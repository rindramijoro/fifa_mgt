package com.mikaz.fifa.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Match {
    private String idMatch;
    private Club home;
    private Integer homeScore;
    private Club away;
    private Integer awayScore;
    private String stadium;
    private LocalDateTime matchDate;
    private Status matchStatus;
    private Season season;
}
