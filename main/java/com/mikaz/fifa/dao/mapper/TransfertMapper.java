package com.mikaz.fifa.dao.mapper;

import com.mikaz.fifa.dao.operations.ClubCRUDOperations;
import com.mikaz.fifa.dao.operations.PlayerCRUDOperations;
import com.mikaz.fifa.model.*;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;

@Component
public class TransfertMapper implements Function<ResultSet, Transfert> {
    private final PlayerCRUDOperations playerCRUDOperations;
    private final ClubCRUDOperations clubCRUDOperations;

    public TransfertMapper(PlayerCRUDOperations playerCRUDOperations, ClubCRUDOperations clubCRUDOperations) {
        this.playerCRUDOperations = playerCRUDOperations;
        this.clubCRUDOperations = clubCRUDOperations;
    }

    @Override
    public Transfert apply(ResultSet resultSet){
        try{
            Transfert transfert = new Transfert();
            UUID idTransfert = resultSet.getObject("id_transfert", UUID.class);
            String idPlayer = resultSet.getString("player");
            UUID idClub = resultSet.getObject("club", UUID.class);

            Player player = playerCRUDOperations.findById(idPlayer);
            Club club = clubCRUDOperations.findClubByIdClub(idClub);

            transfert.setIdTransfert(String.valueOf(idTransfert));
            transfert.setPlayer(player);
            transfert.setClub(club);
            transfert.setTransfertType(TransfertType.valueOf(resultSet.getString("transfert_type")));
            transfert.setTransfertDate(resultSet.getObject("transfert_date", LocalDateTime.class));

            return transfert;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
