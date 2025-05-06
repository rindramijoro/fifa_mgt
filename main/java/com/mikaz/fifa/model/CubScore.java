package com.mikaz.fifa.model;

public class CubScore {
    private String idClubScore;
    private Match game;
    private Club club;
    private Boolean isHome;
    private Integer goalScored;

    public CubScore(String idClubScore, Match game, Club club, Boolean isHome, Integer goalScored) {
        this.idClubScore = idClubScore;
        this.game = game;
        this.club = club;
        this.isHome = isHome;
        this.goalScored = goalScored;
    }

    public CubScore() {}

    public String getIdClubScore() {
        return idClubScore;
    }

    public Match getGame() {
        return game;
    }

    public Club getClub() {
        return club;
    }

    public Boolean getHome() {
        return isHome;
    }

    public Integer getGoalScored() {
        return goalScored;
    }

    public void setIdClubScore(String idClubScore) {
        this.idClubScore = idClubScore;
    }

    public void setGame(Match game) {
        this.game = game;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public void setHome(Boolean home) {
        isHome = home;
    }

    public void setGoalScored(Integer goalScored) {
        this.goalScored = goalScored;
    }
}
