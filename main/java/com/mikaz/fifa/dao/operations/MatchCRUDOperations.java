package com.mikaz.fifa.dao.operations;

import com.mikaz.fifa.dao.DbConnection;
import com.mikaz.fifa.dao.mapper.MatchMapper;
import com.mikaz.fifa.model.Club;
import com.mikaz.fifa.model.Match;
import com.mikaz.fifa.model.Season;
import com.mikaz.fifa.model.Status;
import com.mikaz.fifa.service.exceptions.ClientException;
import com.mikaz.fifa.service.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class MatchCRUDOperations implements CRUDOperations <Match>{
    private final DbConnection dbConnection;
    private final MatchMapper matchMapper;
    private final ClubCRUDOperations clubCRUDOperations;

    public MatchCRUDOperations(DbConnection dbConnection, MatchMapper matchMapper, ClubCRUDOperations clubCRUDOperations) {
        this.dbConnection = dbConnection;
        this.matchMapper = matchMapper;
        this.clubCRUDOperations = clubCRUDOperations;
    }

    @Override
    public List getAll(Integer page, Integer size) {
        return List.of();
    }

    public List<Match> getBySeasonYear(Integer seasonStart){
        List<Match> matches = new ArrayList<>();
        String sql = """
                select m.id_match, m.home, m.away, m.stadium,
                m.match_date, m.match_status, m.season from match m join season s
                on m.season = s.id_season where season_start = ?
                """;
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1,seasonStart);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                matches.add(matchMapper.apply(rs));
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return matches;
    }

    @Override
    public List saveAll(List entities) {
        return List.of();
    }

    public Match updateStatus(UUID idMatch, Status newStatus) {
        String selectSql = "select id_match,home, away, stadium, match_date, match_status, season from match where id_match = ?";
        String updateSql = "update match set match_status = ? where id_match = ?";

        try(Connection conn = dbConnection.getConnection();
            PreparedStatement selectPs = conn.prepareStatement(selectSql))
        {
            selectPs.setObject(1,idMatch);
            ResultSet rs = selectPs.executeQuery();

            if(!rs.next()){
                throw new NotFoundException("Match not found");
            }

            Status currentStatus = Status.valueOf(rs.getString("match_status"));

            if(newStatus.ordinal() <= currentStatus.ordinal()){
                throw new IllegalArgumentException("Invalid status transition");
            }

            try(PreparedStatement updatePs = conn.prepareStatement(updateSql)){
                updatePs.setObject(1,newStatus.name(), java.sql.Types.OTHER);
                updatePs.setObject(2, idMatch);
                updatePs.executeUpdate();
            }

            try(ResultSet rs2 = selectPs.executeQuery()){
                if(rs2.next()){
                    return matchMapper.apply(rs2);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Match> generateMatchesForSeason(Integer seasonStart) {
        List<Match> matches = new ArrayList<>();
        String getSeasonSql = "SELECT id_season, season_status FROM season WHERE season_start = ?";
        String insertMatchSql = """
        INSERT INTO match(id_match, home, away, stadium, match_date, season)
        VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement psSeason = conn.prepareStatement(getSeasonSql)) {

            // Step 1: Get season and create Season object
            psSeason.setInt(1, seasonStart);
            ResultSet rsSeason = psSeason.executeQuery();
            if (!rsSeason.next()) {
                throw new NotFoundException("Season with year " + seasonStart + " not found.");
            }

            UUID seasonId = UUID.fromString(rsSeason.getString("id_season"));
            String seasonStatus = rsSeason.getString("season_status");
            Season season = new Season(seasonId.toString(), seasonStart, seasonStatus); // Ensure Season constructor matches

            if (!"STARTED".equalsIgnoreCase(seasonStatus)) {
                throw new ClientException("Season must be in STARTED status.");
            }

            // Step 2: Check existing matches
            List<Match> existingMatches = getBySeasonYear(seasonStart);
            if (!existingMatches.isEmpty()) {
                return existingMatches;
            }

            // Step 3: Fetch clubs
            List<Club> clubs = clubCRUDOperations.getAll(1, 10);
            if (clubs.size() < 2) {
                throw new ClientException("At least 2 clubs are needed.");
            }

            // Step 4: Generate matches
            AtomicInteger dayOffset = new AtomicInteger(0); // Thread-safe day increment
            matches = clubs.stream()
                    .flatMap(home -> clubs.stream()
                            .filter(away -> !home.getIdClub().equals(away.getIdClub()))
                            .map(away -> {
                                UUID matchId = UUID.randomUUID();
                                LocalDateTime matchDate = LocalDateTime.now().plusDays(dayOffset.getAndIncrement());
                                Timestamp matchTimestamp = Timestamp.valueOf(matchDate);

                                // Insert into match table
                                try (PreparedStatement psInsert = conn.prepareStatement(insertMatchSql)) {
                                    psInsert.setObject(1, matchId);
                                    psInsert.setObject(2, UUID.fromString(home.getIdClub()));
                                    psInsert.setObject(3, UUID.fromString(away.getIdClub()));
                                    psInsert.setString(4, home.getStadium());
                                    psInsert.setTimestamp(5, matchTimestamp);
                                    psInsert.setObject(6, seasonId);
                                    psInsert.executeUpdate();
                                } catch (SQLException e) {
                                    throw new RuntimeException("Error inserting match: " + e.getMessage(), e);
                                }

                                return new Match(
                                        matchId.toString(),
                                        home,
                                        away,
                                        home.getStadium(),
                                        matchDate,
                                        Status.NOT_STARTED,
                                        season
                                );
                            }))
                    .collect(Collectors.toList());

        } catch (SQLException e) {
            throw new RuntimeException("Error generating matches: " + e.getMessage(), e);
        }

        return matches;
    }


}
