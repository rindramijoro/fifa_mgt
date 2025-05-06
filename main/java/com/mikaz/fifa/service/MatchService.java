package com.mikaz.fifa.service;

import com.mikaz.fifa.dao.operations.MatchCRUDOperations;
import com.mikaz.fifa.model.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    private final MatchCRUDOperations matchCRUDOperations;

    public MatchService(MatchCRUDOperations matchCRUDOperations) {
        this.matchCRUDOperations = matchCRUDOperations;
    }

    public List<Match> getMatchesOfASeason(Integer seasonStart) {
        return matchCRUDOperations.getBySeasonYear(seasonStart);
    }
}
