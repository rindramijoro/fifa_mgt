package com.mikaz.fifa.model;

public class Player {
    private String idPlayer;
    private String playerName;
    private Integer jerseyNumber;
    private Integer age;
    private Positions position;
    private String nationality;
    private Club club;

    public Player(String idPlayer, String playerName, Integer jerseyNumber, Integer age, Positions position, String nationality, Club club) {
        this.idPlayer = idPlayer;
        this.playerName = playerName;
        this.jerseyNumber = jerseyNumber;
        this.age = age;
        this.position = position;
        this.nationality = nationality;
        this.club = club;
    }

    public Player() {}

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

    public Club getClub() {
        return club;
    }

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
