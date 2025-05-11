package com.mikaz.fifa.endpoint;

import com.mikaz.fifa.endpoint.rest.CreatePlayer;
import com.mikaz.fifa.model.Player;
import com.mikaz.fifa.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerRestController {
    private final PlayerService playerService;

    public PlayerRestController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public ResponseEntity<Object> getPlayers(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) String playerName,
            @RequestParam(required = false) String clubName,
            @RequestParam(required = false) Integer ageMin,
            @RequestParam(required = false) Integer ageMax
    ){
        return ResponseEntity.ok(playerService.getAllPlayers(ageMin,ageMax,playerName,clubName,page,size));
    }

    @PutMapping("/players")
    public ResponseEntity<Object> createOrUpdatePlayers(@RequestBody List<CreatePlayer> playersToCreate) {
        List<Player> createdPlayers = new ArrayList<>();

        for (CreatePlayer player : playersToCreate) {
            Player createdPlayer = playerService.addPlayer(
                    player.getIdPlayer(),
                    player.getPlayerName(),
                    player.getJerseyNumber(),
                    player.getAge(),
                    player.getPosition(),
                    player.getNationality()
            );
            createdPlayers.add(createdPlayer);
        }

        return ResponseEntity.status(HttpStatus.OK).body(createdPlayers);
    }
}
