package com.mikaz.fifa.dao.operations;

import com.mikaz.fifa.dao.DbConnection;
import com.mikaz.fifa.dao.mapper.ClubMapper;
import com.mikaz.fifa.model.Club;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ClubCRUDOperations  implements  CRUDOperations<Club>{
    private final DbConnection dbConnection;
    private final ClubMapper clubMapper;

    public ClubCRUDOperations(DbConnection dbConnection, ClubMapper clubMapper) {
        this.dbConnection = dbConnection;
        this.clubMapper = clubMapper;
    }

    @Override
    public List<Club> getAll(Integer page,Integer size) {
        int defaultPage = (page != null) ? page : 1;
        int defaultSize = (size != null) ? size : 10;

        List<Club> clubs = new ArrayList<>();
        String sql = """
                select c.id_club, c.club_name, c.acronyme, c.creation_date,c.stadium,
                c.coach,co.id_coach, co.coach_name, co.nationality 
                from club c join coach co on c.coach = co.id_coach  limit ? offset ?
                """;
        try (Connection conn = dbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setInt(1,defaultSize);
            ps.setInt(2,(defaultPage - 1) * defaultSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                clubs.add(clubMapper.apply(rs));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return clubs;
    }

    public Club findClubByName(String clubName) {
        String sql = """
            SELECT c.id_club ,c.club_name, c.acronyme,c.coach, c.creation_date, c.stadium,co.id_coach, co.coach_name
            FROM club c
            JOIN coach co ON c.coach = co.id_coach
            WHERE c.club_name ILIKE ?
            """;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, clubName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return clubMapper.apply(rs);
                }
                throw new RuntimeException("Could not find club " + clubName);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving clubs by name: " + clubName, e);
        }
    }

    public Club findClubByIdClub(UUID idClub) {
        String sql = """
            SELECT c.id_club ,c.club_name, c.acronyme,c.coach, c.creation_date, c.stadium,co.id_coach, co.coach_name
            FROM club c
            JOIN coach co ON c.coach = co.id_coach
            WHERE c.id_club = ?
            """;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, idClub);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return clubMapper.apply(rs);
                }
                throw new RuntimeException("Could not find club " + idClub);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving clubs by name: " + idClub, e);
        }
    }

    public Club findByIdPlayer(UUID idPlayer) {
        String sql = """
            SELECT c.id_club, c.club_name, c.acronyme, c.creation_date, c.stadium, c.coach,
                   co.id_coach,co.coach_name, co.nationality,
                   p.player_name, p.jersey_number, p.position, p.nationality, p.age
            FROM club c
            JOIN player p ON c.id_club = p.club
            JOIN coach co ON c.coach = co.id_coach
            WHERE p.id_player = ?
            """;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, idPlayer);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return clubMapper.apply(rs);
                }
                throw new RuntimeException("Could not find player " + idPlayer);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving clubs for player: " + idPlayer, e);
        }
    }

    @Override
    public List<Club> saveAll(List<Club> entities) {
        String sql = """
                insert into club(club_name, acronyme, coach,creation_date, stadium)
                values(?, ?, ?, ?,?)
                on conflict (club_name)
                do update set club_name=excluded.club_name, acronyme=excluded.acronyme, coach= excluded.coach,stadium=excluded.stadium
                returning club_name, acronyme,coach,creation_date, stadium
                """;
        List<Club> clubs = new ArrayList<>();
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            for(Club club : entities){
                if (club.getCoach() == null) {
                    throw new IllegalArgumentException("Coach is missing for club: " + club.getClubName());
                }
                String coachId = club.getCoach().getIdCoach();
                if (coachId == null || coachId.isEmpty()) {
                    throw new IllegalArgumentException("Coach ID is missing for club: " + club.getClubName());
                }
                ps.setString(1,club.getClubName());
                ps.setString(2,club.getAcronyme());
                ps.setObject(3,UUID.fromString(coachId));
                ps.setInt(4,club.getCreationDate());
                ps.setString(5, club.getStadium());
                ps.addBatch();
            }
            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                while(rs.next()){
                    clubs.add(clubMapper.apply(rs));
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return clubs;
    }
}

