package com.mikaz.fifa.model;

import lombok.Data;

public class Club {
    private String idClub;
    private String clubName;
    private String acronyme;
    private Coach coach;
    private Integer creationDate;
    private String stadium;

    public Club(String idClub, String clubName, String acronyme, Coach coach, Integer creationDate, String stadium) {
        this.idClub = idClub;
        this.clubName = clubName;
        this.acronyme = acronyme;
        this.coach = coach;
        this.creationDate = creationDate;
        this.stadium = stadium;
    }

    public Club() {}

    public String getIdClub() {
        return idClub;
    }

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

    public void setIdClub(String idClub) {
        this.idClub = idClub;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setAcronyme(String acronyme) {
        this.acronyme = acronyme;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

}
