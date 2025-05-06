package com.mikaz.fifa.dao.operations;

import com.mikaz.fifa.dao.DbConnection;
import com.mikaz.fifa.dao.mapper.MatchMapper;
import com.mikaz.fifa.model.Match;
import com.mikaz.fifa.model.Status;
import com.mikaz.fifa.service.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class MatchCRUDOperations implements CRUDOperations <Match>{
    private final DbConnection dbConnection;
    private final MatchMapper matchMapper;

    public MatchCRUDOperations(DbConnection dbConnection, MatchMapper matchMapper) {
        this.dbConnection = dbConnection;
        this.matchMapper = matchMapper;
    }

    @Override
    public List getAll(Integer page, Integer size) {
        return List.of();
    }

    public List<Match> getBySeasonYear(Integer seasonStart){
        List<Match> matches = new ArrayList<>();
        String sql = """
                select m.id_match, m.home, m.away, m.stadium,
                m.match_date, m.match_status, m.season from match m join season s
                on m.season = s.id_season where season_start = ?
                """;
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1,seasonStart);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                matches.add(matchMapper.apply(rs));
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return matches;
    }

    @Override
    public List saveAll(List entities) {
        return List.of();
    }

    public Match updateStatus(UUID idMatch, Status newStatus) {
        String selectSql = "select id_match,home, away, stadium, match_date, match_status, season from match where id_match = ?";
        String updateSql = "update match set match_status = ? where id_match = ?";

        try(Connection conn = dbConnection.getConnection();
            PreparedStatement selectPs = conn.prepareStatement(selectSql))
        {
            selectPs.setObject(1,idMatch);
            ResultSet rs = selectPs.executeQuery();

            if(!rs.next()){
                throw new NotFoundException("Match not found");
            }

            Status currentStatus = Status.valueOf(rs.getString("match_status"));

            if(newStatus.ordinal() <= currentStatus.ordinal()){
                throw new IllegalArgumentException("Invalid status transition");
            }

            try(PreparedStatement updatePs = conn.prepareStatement(updateSql)){
                updatePs.setObject(1,newStatus.name(), java.sql.Types.OTHER);
                updatePs.setObject(2, idMatch);
                updatePs.executeUpdate();
            }

            try(ResultSet rs2 = selectPs.executeQuery()){
                if(rs2.next()){
                    return matchMapper.apply(rs2);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return null;
    }
}
