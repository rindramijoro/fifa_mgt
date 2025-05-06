package com.mikaz.fifa.model;

public class MatchScore {
    private String idMatchScore;
    private Match game;
    private Integer homeScore;
    private Integer awayScore;

    public MatchScore(String idMatchScore, Match game, Integer homeScore, Integer awayScore) {
        this.idMatchScore = idMatchScore;
        this.game = game;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public String getIdMatchScore() {
        return idMatchScore;
    }

    public void setIdMatchScore(String idMatchScore) {
        this.idMatchScore = idMatchScore;
    }

    public Match getGame() {
        return game;
    }

    public void setGame(Match game) {
        this.game = game;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }
}
