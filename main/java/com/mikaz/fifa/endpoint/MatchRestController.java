package com.mikaz.fifa.endpoint;

import com.mikaz.fifa.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
