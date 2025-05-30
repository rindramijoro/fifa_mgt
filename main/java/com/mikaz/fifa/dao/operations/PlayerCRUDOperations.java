package com.mikaz.fifa.dao.operations;

import com.mikaz.fifa.dao.DbConnection;
import com.mikaz.fifa.dao.mapper.PlayerMapper;
import com.mikaz.fifa.model.Player;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class PlayerCRUDOperations implements CRUDOperations<Player> {
    private final DbConnection dbConnection;
    private final PlayerMapper playerMapper;

    public PlayerCRUDOperations(DbConnection dbConnection, PlayerMapper playerMapper) {
        this.dbConnection = dbConnection;
        this.playerMapper = playerMapper;
    }

    public Player findById(String idPlayer) {
        String sql = "SELECT id_player, player_name, jersey_number, age, position, nationality, club FROM player WHERE id_player = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setObject(1, UUID.fromString(idPlayer));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return playerMapper.apply(rs);
                } else {
                    throw new RuntimeException("Could not find player with id " + idPlayer);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving player with id " + idPlayer, e);
        }
    }


    @Override
    public List<Player> getAll(Integer page, Integer size) {
        int defaultPage = (page != null) ? page : 1;
        int defaultSize = (size != null) ? size : 10;

        List<Player> players = new ArrayList<>();
        String sql = """
                 select p.id_player ,p.player_name, p.jersey_number, p.age, p.position, p.nationality,
                 c.club_name, c.acronyme, c.stadium,c.coach, co.id_coach, co.coach_name, co.nationality 
                 from player p join club c on p.club = c.id_club 
                 join coach co on c.coach = co.id_coach limit ? offset ?;
                """;
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setInt(1,defaultSize);
            ps.setInt(2,(defaultPage - 1) * defaultSize);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                players.add(playerMapper.apply(rs));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return players;
    }

    @Override
    public List<Player> saveAll(List<Player> entities) {
       String sql = """
                insert into player(id_player,player_name, jersey_number, age, position, nationality)
                values(?,?,?,?,?,?) 
                on conflict(id_player)     
                do update set player_name = excluded.player_name,jersey_number=excluded.jersey_number,
                age=excluded.age,position=excluded.position,nationality=excluded.nationality
                returning id_player, player_name, jersey_number, age, position, nationality

                """;
       List<Player> players = new ArrayList<>();
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            for(Player player : entities){
                ps.setObject(1, UUID.fromString(player.getIdPlayer()));
                ps.setString(2,player.getPlayerName());
                ps.setInt(3,player.getJerseyNumber());
                ps.setInt(4,player.getAge());
                ps.setObject(5, player.getPosition(), java.sql.Types.OTHER);
                ps.setString(6, player.getNationality());
                ps.addBatch();
            }
            ps.executeBatch();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                while (rs.next()) {
                    players.add(playerMapper.apply(rs));
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return players;
    }
}