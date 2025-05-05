package com.mikaz.fifa.service;

import com.mikaz.fifa.dao.operations.ClubCRUDOperations;
import com.mikaz.fifa.model.Club;
import com.mikaz.fifa.model.Coach;
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

    public Club addClub(String clubName, String acronyme, Coach coach, Integer creationDate, String stadium){
        Club club = new Club();

        club.setClubName(clubName);
        club.setAcronyme(acronyme);
        club.setCoach(coach);
        club.setCreationDate(creationDate);
        club.setStadium(stadium);

        clubCRUDOperations.saveAll(List.of(club));

        return clubCRUDOperations.findClubByName(clubName);
    }
}
