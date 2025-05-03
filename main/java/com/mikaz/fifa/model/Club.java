package com.mikaz.fifa.model;

import lombok.Data;

@Data
public class Club {
    private String idClub;
    private String clubName;
    private String acronyme;
    private Coach coach;
    private Integer creationDate;
    private String stadium;
    private Championship championship;

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

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }
}
