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

}

