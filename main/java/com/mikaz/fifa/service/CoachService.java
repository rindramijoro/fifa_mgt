package com.mikaz.fifa.service;

import com.mikaz.fifa.dao.operations.CoachCRUDOperations;
import com.mikaz.fifa.model.Coach;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService {
    private final CoachCRUDOperations coachCRUDOperations;

    public CoachService(CoachCRUDOperations coachCRUDOperations) {
        this.coachCRUDOperations = coachCRUDOperations;
    }

    public List<Coach> getAllCoaches(int page, int size){
        return coachCRUDOperations.getAll(page, size);
    }
}
