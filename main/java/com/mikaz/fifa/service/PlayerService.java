package com.mikaz.fifa.service;

import com.mikaz.fifa.dao.operations.PlayerCRUDOperations;
import com.mikaz.fifa.model.Player;
import com.mikaz.fifa.model.Positions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerCRUDOperations playerCRUDOperations;

    public PlayerService(PlayerCRUDOperations playerCRUDOperations) {
        this.playerCRUDOperations = playerCRUDOperations;
    }

    public List<Player> getAllPlayers(int page, int size) {
        return playerCRUDOperations.getAll(page,size);
    }

    public Player addPlayer(String idPlayer, String playerName, Integer jerseyNumber, Integer age, Positions position, String nationality){
        Player player = new Player();

        player.setIdPlayer(idPlayer);
        player.setPlayerName(playerName);
        player.setJerseyNumber(jerseyNumber);
        player.setAge(age);
        player.setPosition(position);
        player.setNationality(nationality);

        playerCRUDOperations.saveAll(List.of(player));

        return playerCRUDOperations.findById(idPlayer);
    }
}
