package com.mikaz.fifa.dao.mapper;

import com.mikaz.fifa.dao.operations.ClubCRUDOperations;
import com.mikaz.fifa.dao.operations.SeasonCRUDOperations;
import com.mikaz.fifa.model.Club;
import com.mikaz.fifa.model.Match;
import com.mikaz.fifa.model.Season;
import com.mikaz.fifa.model.Status;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;

@Component
public class MatchMapper implements Function<ResultSet, Match> {
    private final ClubCRUDOperations clubCRUDOperations;
    private final SeasonCRUDOperations seasonCRUDOperations;

    public MatchMapper(ClubCRUDOperations clubCRUDOperations, SeasonCRUDOperations seasonCRUDOperations) {
        this.clubCRUDOperations = clubCRUDOperations;
        this.seasonCRUDOperations = seasonCRUDOperations;
    }

    @Override
    public Match apply(ResultSet resultSet) {
        try{
            Match match = new Match();
            UUID idMatch = resultSet.getObject("id_match",UUID.class);
            UUID idHome = resultSet.getObject("home",UUID.class);
            UUID idAway = resultSet.getObject("away",UUID.class);
            UUID idSeason = resultSet.getObject("season",UUID.class);
            Season season = seasonCRUDOperations.findByIdSeason(idSeason);
            Club home = clubCRUDOperations.findClubByIdClub(idHome);
            Club away = clubCRUDOperations.findClubByIdClub(idAway);

            match.setIdMatch(String.valueOf(idMatch));
            match.setHome(home);
            match.setAway(away);
            match.setStadium(resultSet.getString("stadium"));
            match.setMatchDate(resultSet.getObject("match_date", LocalDateTime.class));
            match.setMatchStatus(Status.valueOf(resultSet.getString("match_status")));
            match.setSeason(season);
            return match;
        }catch (SQLException e){
            throw new RuntimeException("Failed to map ResultSet to club", e);
        }
    }
}
