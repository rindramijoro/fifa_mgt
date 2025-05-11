package com.mikaz.fifa.endpoint;

import com.mikaz.fifa.endpoint.rest.CreateClub;
import com.mikaz.fifa.model.Club;
import com.mikaz.fifa.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClubRestController {
    private final ClubService clubService;

    public ClubRestController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public ResponseEntity<Object> getClubs(
            @RequestParam(required = false,defaultValue = "1") int page,
            @RequestParam(required = false,defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(clubService.getAllClubs(page, size));
    }

    @PutMapping("/clubs")
    public ResponseEntity<Object> createOrUpdateClubs(@RequestBody List<CreateClub> clubsToCreate) {
        List<Club> createdClubs = new ArrayList<>();

        for (CreateClub clubToCreate : clubsToCreate) {
            Club createdClub = clubService.addClub(
                    clubToCreate.getIdClub(),
                    clubToCreate.getClubName(),
                    clubToCreate.getAcronyme(),
                    clubToCreate.getCoach(),
                    clubToCreate.getCreationDate(),
                    clubToCreate.getStadium()
            );
            createdClubs.add(createdClub);
        }

        return ResponseEntity.status(HttpStatus.OK).body(createdClubs);
    }
}

