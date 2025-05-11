package com.mikaz.fifa.dao.operations;

import com.mikaz.fifa.dao.DbConnection;
import com.mikaz.fifa.dao.mapper.TransfertMapper;
import com.mikaz.fifa.model.Transfert;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransfertCRUDOperations implements CRUDOperations <Transfert>{

    private final DbConnection dbConnection;
    private final TransfertMapper transfertMapper;

    public TransfertCRUDOperations(DbConnection dbConnection, TransfertMapper transfertMapper) {
        this.dbConnection = dbConnection;
        this.transfertMapper = transfertMapper;
    }

    @Override
    public List<Transfert> getAll(Integer page, Integer size) {
        int defaultPage = (page != null) ? page : 1;
        int defaultSize = (size != null) ? size : 10;

        List<Transfert> transferts = new ArrayList<>();

        String sql = """
                select t.id_transfert, t.player, t.club, t.transfert_type, t.transfert_date,
                p.id_player ,p.player_name, p.jersey_number, p.age, p.position, p.nationality,
                c.club_name, c.acronyme, c.stadium,c.coach, co.id_coach, co.coach_name, co.nationality
                from transfert t join player p on t.player = p.id_player
                join club c on t.club = c.id_club join coach co on c.coach = co.id_coach limit ? offset ?
                """;
        try(Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1,defaultSize);
            ps.setInt(2,(defaultPage - 1) * defaultSize);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                transferts.add(transfertMapper.apply(rs));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return transferts;
    }

    @Override
    public List<Transfert> saveAll(List<Transfert> entities) {
        return List.of();
    }
}
