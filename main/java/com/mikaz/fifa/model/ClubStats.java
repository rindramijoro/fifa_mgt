package com.mikaz.fifa.model;

public class ClubStats {
    private String idClubStats;
    private Club club;
    private Integer scored;
    private Integer against;
    private Integer goalDifference;
    private Integer cleanSheets;
    private Integer points;
    private Season season;

    public ClubStats(String idClubStats, Club club, Integer scored, Integer against, Integer goalDifference, Integer cleanSheets, Integer points, Season season) {
        this.idClubStats = idClubStats;
        this.club = club;
        this.scored = scored;
        this.against = against;
        this.goalDifference = goalDifference;
        this.cleanSheets = cleanSheets;
        this.points = points;
        this.season = season;
    }

    public ClubStats() {}

    public String getIdClubStats() {
        return idClubStats;
    }

    public void setIdClubStats(String idClubStats) {
        this.idClubStats = idClubStats;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Integer getScored() {
        return scored;
    }

    public void setScored(Integer scored) {
        this.scored = scored;
    }

    public Integer getAgainst() {
        return against;
    }

    public void setAgainst(Integer against) {
        this.against = against;
    }

    public Integer getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(Integer goalDifference) {
        this.goalDifference = goalDifference;
    }

    public Integer getCleanSheets() {
        return cleanSheets;
    }

    public void setCleanSheets(Integer cleanSheets) {
        this.cleanSheets = cleanSheets;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}
