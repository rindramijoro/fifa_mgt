package com.mikaz.fifa.service;

import com.mikaz.fifa.dao.operations.ClubStatsCRUDOperations;
import com.mikaz.fifa.model.ClubStats;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubStatService {
    private final ClubStatsCRUDOperations clubStatsCRUDOperations;

    public ClubStatService(ClubStatsCRUDOperations clubStatsCRUDOperations) {
        this.clubStatsCRUDOperations = clubStatsCRUDOperations;
    }

    public List<ClubStats> getAllClubStatsOfASeason(Integer seasonStart) {
        return clubStatsCRUDOperations.getBySeasonYear(seasonStart);
    }
}
