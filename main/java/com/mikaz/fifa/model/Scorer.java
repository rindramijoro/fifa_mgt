package com.mikaz.fifa.model;

public class Scorer {
    private String idScorer;
    private Match game;
    private Player player;
    private Integer minute;
    private Boolean ownGoal;

    public Scorer() {
    }

    public String getIdScorer() {
        return this.idScorer;
    }

    public Match getGame() {
        return this.game;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Integer getMinute() {
        return this.minute;
    }

    public Boolean getOwnGoal() {
        return this.ownGoal;
    }

    public void setIdScorer(String idScorer) {
        this.idScorer = idScorer;
    }

    public void setGame(Match game) {
        this.game = game;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public void setOwnGoal(Boolean ownGoal) {
        this.ownGoal = ownGoal;
    }
}
