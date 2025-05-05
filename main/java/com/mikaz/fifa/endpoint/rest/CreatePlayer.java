package com.mikaz.fifa.endpoint.rest;

import com.mikaz.fifa.model.Positions;

public class CreatePlayer {
    private String idPlayer;
    private String playerName;
    private Integer jerseyNumber;
    private Integer age;
    private Positions position;
    private String nationality;

    public CreatePlayer(String idPlayer, String playerName, Integer jerseyNumber, Integer age, Positions position, String nationality) {
        this.idPlayer = idPlayer;
        this.playerName = playerName;
        this.jerseyNumber = jerseyNumber;
        this.age = age;
        this.position = position;
        this.nationality = nationality;
    }

    public CreatePlayer() {}

    public String getIdPlayer() {
        return idPlayer;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public Integer getAge() {
        return age;
    }

    public Positions getPosition() {
        return position;
    }

    public String getNationality() {
        return nationality;
    }
}
