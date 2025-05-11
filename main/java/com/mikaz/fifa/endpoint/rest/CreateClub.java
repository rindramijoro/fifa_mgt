package com.mikaz.fifa.endpoint.rest;

import com.mikaz.fifa.model.Coach;

public class CreateClub {
    private String idClub;
    private String clubName;
    private String acronyme;
    private Coach coach;
    private Integer creationDate;
    private String stadium;

    public CreateClub(String idClub, String clubName, String acronyme, Coach coach, Integer creationDate, String stadium) {
        this.idClub = idClub;
        this.clubName = clubName;
        this.acronyme = acronyme;
        this.coach = coach;
        this.creationDate = creationDate;
        this.stadium = stadium;
    }

    public String getIdClub() {
        return idClub;
    }

    public void setIdClub(String idClub) {
        this.idClub = idClub;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getAcronyme() {
        return acronyme;
    }

    public void setAcronyme(String acronyme) {
        this.acronyme = acronyme;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }
}
