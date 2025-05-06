package com.mikaz.fifa.dao.operations;

import com.mikaz.fifa.dao.DbConnection;
import com.mikaz.fifa.dao.mapper.CoachMapper;
import com.mikaz.fifa.model.Coach;
import com.mikaz.fifa.model.Season;
import com.mikaz.fifa.model.Status;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CoachCRUDOperations implements CRUDOperations <Coach>{
    private final DbConnection dbConnection;
    private final CoachMapper coachMapper;

    public CoachCRUDOperations(DbConnection dbConnection, CoachMapper coachMapper) {
        this.dbConnection = dbConnection;
        this.coachMapper = coachMapper;
    }

    public Coach findByIdCoach(UUID idCoach) {
        String sql = "SELECT id_coach, coach_name, nationality FROM coach WHERE id_coach = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setObject(1, idCoach);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return coachMapper.apply(rs);
                }
                throw new RuntimeException("Could not find coach " + idCoach);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding coach: " + idCoach, e);
        }
    }

    @Override
    public List<Coach> getAll(Integer page, Integer size) {
        int defaultPage = (page != null) ? page : 1;
        int defaultSize = (size != null) ? size : 10;

        List<Coach> coaches = new ArrayList<>();
        String sql = "SELECT id_coach, coach_name, nationality FROM coach limit ? offset ?";
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setInt(1,defaultSize);
            ps.setInt(2,(defaultPage - 1) * defaultSize);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                coaches.add(coachMapper.apply(rs));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coaches;
    }

    @Override
    public List<Coach> saveAll(List<Coach> entities) {
        return List.of();
    }

    @Override
    public Season updateStatus(Integer seasonStart, Status newStatus) {
        return null;
    }
}
