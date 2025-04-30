package com.mikaz.fifa.model;

import lombok.Data;

@Data
public class Season {
    private String idSeason;
    private Championship championship;
    private Integer seasonStart;
    private Integer seasonEnd;
    private Status seasonStatus;
}
