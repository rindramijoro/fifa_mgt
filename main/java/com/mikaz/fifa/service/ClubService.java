package com.mikaz.fifa.service;

import com.mikaz.fifa.dao.operations.ClubCRUDOperations;
import com.mikaz.fifa.model.Club;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {
    private final ClubCRUDOperations clubCRUDOperations;

    public ClubService(ClubCRUDOperations clubCRUDOperations) {
        this.clubCRUDOperations = clubCRUDOperations;
    }

    public List<Club> getAllClubs(int page,int size ) {
        return clubCRUDOperations.getAll(page,size);
    }
}
