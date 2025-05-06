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

    public List<Player> getAllPlayers(Integer ageMin, Integer ageMax,int page, int size) {
        if(ageMin != null && ageMax != null && ageMin > ageMax) {
            throw new IllegalArgumentException("ageMax must be greater than ageMin");
        }
        if(ageMin != null && ageMin < 15) {
            throw new IllegalArgumentException("ageMin must be greater than 15");
        }

        List<Player> players = playerCRUDOperations.getAll(page,size);

        return players.stream()
                .filter(player -> {
                    Integer age = player.getAge();
                    if(ageMin != null && ageMax != null) {
                        return age >= ageMin && age <= ageMax;
                    }
                    if (ageMin != null) {
                        return age >= ageMin;
                    }
                    if(ageMax != null) {
                        return age <= ageMin;
                    }
                    return true;
                }).toList();
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
