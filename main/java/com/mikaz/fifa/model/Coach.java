package com.mikaz.fifa.model;

public class Coach {
    private String idCoach;
    private String coachName;
    private String nationality;

    public Coach(String idCoach,String coachName, String nationality) {
        this.idCoach = idCoach;
        this.coachName = coachName;
        this.nationality = nationality;
    }

    public Coach() {}

    public String getIdCoach() {
        return idCoach;
    }

    public String getCoachName() {
        return coachName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setIdCoach(String idCoach) {
        this.idCoach = idCoach;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
