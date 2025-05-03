package com.mikaz.fifa.endpoint;

import com.mikaz.fifa.service.CoachService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoachRestController {
    private final CoachService coachService;

    public CoachRestController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/coaches")
    public ResponseEntity<Object> getCoaches(
            @RequestParam(required = false,defaultValue = "1") int page,
            @RequestParam(required = false,defaultValue = "10") int size
    ){
        return ResponseEntity.ok(coachService.getAllCoaches(page, size));
    }
}
