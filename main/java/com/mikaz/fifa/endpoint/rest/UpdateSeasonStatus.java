package com.mikaz.fifa.endpoint.rest;

import com.mikaz.fifa.model.Status;

public class UpdateSeasonStatus {
    private Status seasonStatus;

    public UpdateSeasonStatus(Status seasonStatus) {
        this.seasonStatus = seasonStatus;
    }

    public Status getSeasonStatus() {
        return seasonStatus;
    }
}
