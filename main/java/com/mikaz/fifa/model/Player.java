package com.mikaz.fifa.model;

import lombok.Data;

@Data
public class Player {
    private String idPlayer;
    private String playerName;
    private Integer jerseyNumber;
    private Integer age;
    private Positions position;
    private String nationality;
    private Club club;
}
