package com.mikaz.fifa.service;

import com.mikaz.fifa.dao.operations.PlayerCRUDOperations;
import com.mikaz.fifa.model.Player;
import lombok.RequiredArgsConstructor;
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
}
