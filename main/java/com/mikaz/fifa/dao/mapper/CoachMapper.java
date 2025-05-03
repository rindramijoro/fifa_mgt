package com.mikaz.fifa.dao.mapper;

import com.mikaz.fifa.model.Coach;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CoachMapper implements Function<ResultSet, Coach> {

    @Override
    public Coach apply(ResultSet resultSet) {
        try {
            Coach coach = new Coach();
            String idCoach = resultSet.getString("id_coach");

            coach.setIdCoach(idCoach);
            coach.setCoachName(resultSet.getString("coach_name"));
            coach.setNationality(resultSet.getString("nationality"));

            return coach;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to map ResultSet to Coach", e);
        }
    }
}
