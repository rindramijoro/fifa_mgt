package com.mikaz.fifa.endpoint;

import com.mikaz.fifa.service.TransfertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransfertRestController {
    private final TransfertService transfertService;

    public TransfertRestController(TransfertService transfertService) {
        this.transfertService = transfertService;
    }

    @GetMapping("/transferts")
    public ResponseEntity<Object> getTransferts(
            @RequestParam(required = false,defaultValue = "1") int page,
            @RequestParam(required = false,defaultValue = "10") int size
    ){
        return ResponseEntity.ok(transfertService.getAllTransferts(page,size));
    }
}
