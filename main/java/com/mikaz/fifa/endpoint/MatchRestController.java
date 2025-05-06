package com.mikaz.fifa.endpoint;

import com.mikaz.fifa.endpoint.rest.UpdateMatchStatus;
import com.mikaz.fifa.model.Match;
import com.mikaz.fifa.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class MatchRestController {
    private final MatchService matchService;

    public MatchRestController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/matches/{seasonStart}")
    public ResponseEntity<Object> getMatchesOfOSeason(@PathVariable Integer seasonStart) {
        return ResponseEntity.ok(matchService.getMatchesOfASeason(seasonStart));
    }

    @PutMapping("/matches/{idMatch}/status")
    public ResponseEntity<Object> updateMatchStatus(
            @PathVariable UUID idMatch,
            @RequestBody UpdateMatchStatus matchStatus
    ){
        Match updated = matchService.updateMatchStatus(idMatch, matchStatus.getMatchStatus());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
