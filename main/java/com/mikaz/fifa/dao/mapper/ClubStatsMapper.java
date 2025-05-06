package com.mikaz.fifa.dao.mapper;

import com.mikaz.fifa.dao.operations.ClubCRUDOperations;
import com.mikaz.fifa.dao.operations.SeasonCRUDOperations;
import com.mikaz.fifa.model.Club;
import com.mikaz.fifa.model.ClubStats;
import com.mikaz.fifa.model.Season;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Function;

@Component
public class ClubStatsMapper implements Function<ResultSet, ClubStats> {
    private final ClubCRUDOperations clubCRUDOperations;
    private final SeasonCRUDOperations seasonCRUDOperations;

    public ClubStatsMapper(ClubCRUDOperations clubCRUDOperations, SeasonCRUDOperations seasonCRUDOperations) {
        this.clubCRUDOperations = clubCRUDOperations;
        this.seasonCRUDOperations = seasonCRUDOperations;
    }

    @Override
    public ClubStats apply(ResultSet resultSet) {
        try {
            ClubStats clubStats = new ClubStats();
            UUID idClub = resultSet.getObject("club",UUID.class);
            UUID idSeason = resultSet.getObject("season",UUID.class);
            Club club = clubCRUDOperations.findClubByIdClub(idClub);
            Season season = seasonCRUDOperations.findByIdSeason(idSeason);

            clubStats.setIdClubStats(resultSet.getString("id_club_stats"));
            clubStats.setClub(club);
            clubStats.setScored(resultSet.getInt("scored"));
            clubStats.setAgainst(resultSet.getInt("against"));
            clubStats.setGoalDifference(resultSet.getInt("goal_difference"));
            clubStats.setCleanSheets(resultSet.getInt("clean_sheets"));
            clubStats.setPoints(resultSet.getInt("points"));
            clubStats.setSeason(season);

            return clubStats;
        }catch (SQLException e) {
            throw new RuntimeException("Failed to map rs to club stats", e);
        }
    }
}

