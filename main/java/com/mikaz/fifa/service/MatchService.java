package com.mikaz.fifa.service;

import com.mikaz.fifa.dao.operations.MatchCRUDOperations;
import com.mikaz.fifa.model.Match;
import com.mikaz.fifa.model.Status;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MatchService {
    private final MatchCRUDOperations matchCRUDOperations;

    public MatchService(MatchCRUDOperations matchCRUDOperations) {
        this.matchCRUDOperations = matchCRUDOperations;
    }

    public List<Match> getMatchesOfASeason(Integer seasonStart) {
        return matchCRUDOperations.getBySeasonYear(seasonStart);
    }

    public Match updateMatchStatus(UUID idMatch, Status newStatus){
        return matchCRUDOperations.updateStatus(idMatch, newStatus);
    }
}
