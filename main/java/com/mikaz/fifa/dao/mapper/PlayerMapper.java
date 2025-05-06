package com.mikaz.fifa.dao.mapper;

import com.mikaz.fifa.dao.operations.ClubCRUDOperations;
import com.mikaz.fifa.model.Club;
import com.mikaz.fifa.model.Player;
import com.mikaz.fifa.model.Positions;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.function.Function;

@Component
public class PlayerMapper implements Function<ResultSet, Player> {
    private final ClubCRUDOperations clubCRUDOperations;

    public PlayerMapper(ClubCRUDOperations clubCRUDOperations) {
        this.clubCRUDOperations = clubCRUDOperations;
    }

    @Override
    public Player apply(ResultSet resultSet) {
        try {
            Player player = new Player();
            UUID idPlayer = resultSet.getObject("id_player",UUID.class);

            Club club = null;
            try {
                club = clubCRUDOperations.findByIdPlayer(idPlayer);
            }catch (Exception e){
                System.err.println("Warning: No club found for player with ID " + idPlayer + ". Reason: " + e.getMessage());
            }

            player.setIdPlayer(resultSet.getString("id_player"));
            player.setPlayerName(resultSet.getString("player_name"));
            player.setJerseyNumber(resultSet.getInt("jersey_number"));
            player.setAge(resultSet.getInt("age"));
            player.setPosition(Positions.valueOf(resultSet.getString("position")));
            player.setNationality(resultSet.getString("nationality"));
            player.setClub(club);

            return player;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to map ResultSet to Player", e);
        }
    }
}
