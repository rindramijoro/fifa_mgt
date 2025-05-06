package com.mikaz.fifa.service;

import com.mikaz.fifa.dao.operations.SeasonCRUDOperations;
import com.mikaz.fifa.model.Season;
import com.mikaz.fifa.model.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonService {
    private final SeasonCRUDOperations seasonCRUDOperations;

    public SeasonService(SeasonCRUDOperations seasonCRUDOperations) {
        this.seasonCRUDOperations = seasonCRUDOperations;
    }

    public List<Season> getAllSeasons(int page, int size){
        return seasonCRUDOperations.getAll(page, size);
    }

    public List<Season> saveAll(List<Season> seasons){
        return seasonCRUDOperations.saveAll(seasons);
    }

    public Season updateSeasonStatus(Integer seasonStart, Status newStatus){
        return seasonCRUDOperations.updateStatus(seasonStart,newStatus);
    }
}
