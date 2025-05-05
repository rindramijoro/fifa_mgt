package com.mikaz.fifa.endpoint;

import com.mikaz.fifa.endpoint.rest.CreateClub;
import com.mikaz.fifa.model.Club;
import com.mikaz.fifa.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> createOrUpdateClub(@RequestBody CreateClub clubToCreate){
        Club createdClub = clubService.addClub(
                clubToCreate.getClubName(),
                clubToCreate.getAcronyme(),
                clubToCreate.getCoach(),
                clubToCreate.getCreationDate(),
                clubToCreate.getStadium()
        );

        return ResponseEntity.status(HttpStatus.OK).body(createdClub);
    }
}

