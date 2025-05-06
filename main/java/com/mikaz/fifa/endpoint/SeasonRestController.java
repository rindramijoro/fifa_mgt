package com.mikaz.fifa.endpoint;

import com.mikaz.fifa.endpoint.rest.CreateSeason;
import com.mikaz.fifa.model.Season;
import com.mikaz.fifa.service.SeasonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeasonRestController {
    public final SeasonService seasonService;

    public SeasonRestController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping("/seasons")
    public ResponseEntity<Object> getSeasons(
            @RequestParam(required = false,defaultValue = "1") int page,
            @RequestParam(required = false,defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(seasonService.getAllSeasons(page, size));
    }

    @PostMapping("/seasons")
    public ResponseEntity<Object> createSeason(@RequestBody CreateSeason seasonToCreate) {
        Season season = new Season();
        season.setSeasonStart(seasonToCreate.getSeasonStart());
        season.setAlias(seasonToCreate.getAlias());

        List<Season> savedSeasons = seasonService.saveAll(List.of(season));

        return new ResponseEntity<>(savedSeasons.get(0), HttpStatus.CREATED);
    }
}

