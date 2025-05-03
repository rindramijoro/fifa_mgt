package com.mikaz.fifa.endpoint;

import com.mikaz.fifa.service.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
