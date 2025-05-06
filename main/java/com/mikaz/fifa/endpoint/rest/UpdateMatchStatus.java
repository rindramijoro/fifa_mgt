package com.mikaz.fifa.endpoint.rest;

import com.mikaz.fifa.model.Status;

public class UpdateMatchStatus {
    private Status matchStatus;

    public UpdateMatchStatus(Status matchStatus) {
        this.matchStatus = matchStatus;
    }

    public Status getMatchStatus() {
        return matchStatus;
    }
}
