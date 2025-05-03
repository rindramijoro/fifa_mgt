package com.mikaz.fifa.dao.mapper;

import com.mikaz.fifa.dao.operations.ClubCRUDOperations;
import com.mikaz.fifa.model.Club;
import com.mikaz.fifa.model.Player;
import com.mikaz.fifa.model.Positions;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            String playerName = resultSet.getString("player_name");

            Club club = clubCRUDOperations.findByPlayerName(playerName)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No club found for player: " + playerName));

            player.setPlayerName(playerName);
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
