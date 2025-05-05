package com.mikaz.fifa.endpoint.rest;

import com.mikaz.fifa.model.Coach;

public class CreateClub {
    private String clubName;
    private String acronyme;
    private Coach coach;
    private Integer creationDate;
    private String stadium;

    public CreateClub(String clubName, String acronyme, Coach coach, Integer creationDate, String stadium) {
        this.clubName = clubName;
        this.acronyme = acronyme;
        this.coach = coach;
        this.creationDate = creationDate;
        this.stadium = stadium;
    }

    public CreateClub() {}

    public String getClubName() {
        return clubName;
    }

    public String getAcronyme() {
        return acronyme;
    }

    public Coach getCoach() {
        return coach;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public String getStadium() {
        return stadium;
    }
}
