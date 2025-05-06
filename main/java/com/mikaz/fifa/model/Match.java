package com.mikaz.fifa.model;

import java.time.LocalDateTime;

public class Match {
    private String idMatch;
    private Club home;
    private Club away;
    private String stadium;
    private LocalDateTime matchDate;
    private Status matchStatus;
    private Season season;

    public Match(String idMatch, Club home, Club away, String stadium, LocalDateTime matchDate, Status matchStatus, Season season) {
        this.idMatch = idMatch;
        this.home = home;
        this.away = away;
        this.stadium = stadium;
        this.matchDate = matchDate;
        this.matchStatus = matchStatus;
        this.season = season;
    }

    public Match() {}

    public String getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    public Club getHome() {
        return home;
    }

    public void setHome(Club home) {
        this.home = home;
    }

    public Club getAway() {
        return away;
    }

    public void setAway(Club away) {
        this.away = away;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public Status getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Status matchStatus) {
        this.matchStatus = matchStatus;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}
