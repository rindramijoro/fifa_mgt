package com.mikaz.fifa.dao.mapper;

import com.mikaz.fifa.model.Season;
import com.mikaz.fifa.model.Status;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

@Component
public class SeasonMapper implements Function<ResultSet, Season> {

    @Override
    public Season apply(ResultSet resultSet) {
        try{
            Season season = new Season();

            season.setIdSeason(resultSet.getString("id_season"));
            season.setSeasonStart(resultSet.getInt("season_start"));
            season.setAlias(resultSet.getString("alias"));
            season.setSeasonStatus(Status.valueOf(resultSet.getString("season_status")));

            return season;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
