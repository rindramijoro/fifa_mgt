package com.mikaz.fifa.model;

import lombok.Data;

@Data
public class Club {
    private String idClub;
    private String clubName;
    private String acronyme;
    private Coach coach;
    private Integer creationDate;
    private String stadium;
    private Championship championship;
}
