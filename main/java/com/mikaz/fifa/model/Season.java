package com.mikaz.fifa.model;

public class Season {
    private String idSeason;
    private Integer seasonStart;
    private String alias;
    private Status seasonStatus;

    public Season(String idSeason, Integer seasonStart, String alias, Status seasonStatus) {
        this.idSeason = idSeason;
        this.seasonStart = seasonStart;
        this.alias = alias;
        this.seasonStatus = seasonStatus;
    }

    public Season() {}

    public String getIdSeason() {
        return idSeason;
    }

    public void setIdSeason(String idSeason) {
        this.idSeason = idSeason;
    }

    public Integer getSeasonStart() {
        return seasonStart;
    }

    public void setSeasonStart(Integer seasonStart) {
        this.seasonStart = seasonStart;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Status getSeasonStatus() {
        return seasonStatus;
    }

    public void setSeasonStatus(Status seasonStatus) {
        this.seasonStatus = seasonStatus;
    }
}
