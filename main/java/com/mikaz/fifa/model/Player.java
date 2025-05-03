package com.mikaz.fifa.model;

import lombok.Data;

@Data
public class Player {
    private String idPlayer;
    private String playerName;
    private Integer jerseyNumber;
    private Integer age;
    private Positions position;
    private String nationality;
    private Club club;

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
