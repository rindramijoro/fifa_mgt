package com.mikaz.fifa.model;

import lombok.Data;

@Data
public class Goals {
    private String idGoal;
    private Player goalScorer;
    private Match game;
    private Integer goalScored;
}
