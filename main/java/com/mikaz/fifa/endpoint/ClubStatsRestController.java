package com.mikaz.fifa.endpoint;

import com.mikaz.fifa.service.ClubStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClubStatsRestController {
    private final ClubStatService clubStatService;

    public ClubStatsRestController(ClubStatService clubStatService) {
        this.clubStatService = clubStatService;
    }

    @GetMapping("/clubs/statistics/{seasonStart}")
    public ResponseEntity<Object> getClubStatsOfASeason(@PathVariable Integer seasonStart) {
        return ResponseEntity.ok(clubStatService.getAllClubStatsOfASeason(seasonStart));
    }
}
