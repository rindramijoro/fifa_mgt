package com.mikaz.fifa.model;

import lombok.Data;

@Data
public class ClubStats {
    private String idClubStats;
    private Club club;
    private Integer scored;
    private Integer against;
    private Integer goalDifference;
    private Integer cleanSheets;
    private Integer points;
    private Season season;
}
