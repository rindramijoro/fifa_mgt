package com.mikaz.fifa.model;

import java.time.LocalDateTime;

public class Transfert {
    private String idTransfert;
    private Player player;
    private Club club;
    private TransfertType transfertType;
    private LocalDateTime transfertDate;

    public Transfert(String idTransfert, Player player, Club club, TransfertType transfertType, LocalDateTime transfertDate) {
        this.idTransfert = idTransfert;
        this.player = player;
        this.club = club;
        this.transfertType = transfertType;
        this.transfertDate = transfertDate;
    }

    public Transfert() {}

    public String getIdTransfert() {
        return idTransfert;
    }

    public void setIdTransfert(String idTransfert) {
        this.idTransfert = idTransfert;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public TransfertType getTransfertType() {
        return transfertType;
    }

    public void setTransfertType(TransfertType transfertType) {
        this.transfertType = transfertType;
    }

    public LocalDateTime getTransfertDate() {
        return transfertDate;
    }

    public void setTransfertDate(LocalDateTime transfertDate) {
        this.transfertDate = transfertDate;
    }
}
