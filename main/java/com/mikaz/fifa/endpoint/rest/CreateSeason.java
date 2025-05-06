package com.mikaz.fifa.endpoint.rest;

public class CreateSeason {
    private Integer seasonStart;
    private String alias;

    public CreateSeason(Integer seasonStart, String alias) {
        this.seasonStart = seasonStart;
        this.alias = alias;
    }

    public CreateSeason() {}

    public Integer getSeasonStart() {
        return seasonStart;
    }

    public String getAlias() {
        return alias;
    }
}
