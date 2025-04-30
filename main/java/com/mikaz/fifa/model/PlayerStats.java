package com.mikaz.fifa.model;
import lombok.Data;

@Data
public class PlayerStats {
    private String idPlayerStats;
    private Player player;
    private Integer totalGoal;
    private Integer timePlayed;
    private Season season;
}
