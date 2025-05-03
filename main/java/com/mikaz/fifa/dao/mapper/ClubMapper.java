package com.mikaz.fifa.dao.mapper;

import com.mikaz.fifa.dao.operations.CoachCRUDOperations;
import com.mikaz.fifa.model.Club;
import com.mikaz.fifa.model.Coach;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Function;

@Component
public class ClubMapper implements Function<ResultSet, Club> {
    private final CoachCRUDOperations coachCRUDOperations;

    public ClubMapper(CoachCRUDOperations coachCRUDOperations) {
        this.coachCRUDOperations = coachCRUDOperations;
    }

    @Override
    public Club apply(ResultSet resultSet) {
        try {
            Club club = new Club();
            UUID idCoach = resultSet.getObject("id_coach", UUID.class);
            Coach coach = coachCRUDOperations.findByIdCoach(idCoach);


            club.setIdClub(resultSet.getString("id_club"));
            club.setClubName(resultSet.getString("club_name"));
            club.setAcronyme(resultSet.getString("acronyme"));
            club.setCreationDate(resultSet.getInt("creation_date"));
            club.setStadium(resultSet.getString("stadium"));
            club.setCoach(coach);
            return club;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to map ResultSet to Club", e);
        }
    }
}
