package com.mikaz.fifa.dao.operations;

import com.mikaz.fifa.dao.DbConnection;
import com.mikaz.fifa.dao.mapper.ClubMapper;
import com.mikaz.fifa.model.Club;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        return List.of();
    }

    public List<Club> findClubByName(String clubName) {
        List<Club> clubs = new ArrayList<>();
        String sql = """
            SELECT c.club_name, c.acronyme, c.creation_date, c.stadium, co.coach_name
            FROM club c
            JOIN coach co ON c.coach_id = co.coach_id
            WHERE c.club_name ILIKE ?
            """;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, clubName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    clubs.add(clubMapper.apply(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving clubs by name: " + clubName, e);
        }

        return clubs;
    }

    public List<Club> findByPlayerName(String playerName) {
        List<Club> clubs = new ArrayList<>();
        String sql = """
            SELECT c.club_name, c.acronyme, c.creation_date, c.stadium,
                   co.coach_name, co.nationality,
                   p.player_name, p.jersey_number, p.position, p.nationality, p.age
            FROM club c
            JOIN player p ON c.id_club = p.club
            JOIN coach co ON c.id_coach = co.id_coach
            WHERE p.player_name ILIKE ?
            """;

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, playerName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    clubs.add(clubMapper.apply(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving clubs for player: " + playerName, e);
        }

        return clubs;
    }

}

