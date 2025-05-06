package com.mikaz.fifa.dao.operations;

import com.mikaz.fifa.dao.DbConnection;
import com.mikaz.fifa.dao.mapper.MatchMapper;
import com.mikaz.fifa.model.Match;
import com.mikaz.fifa.model.Season;
import com.mikaz.fifa.model.Status;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MatchCRUDOperations implements CRUDOperations {
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

    @Override
    public Season updateStatus(Integer seasonStart, Status newStatus) {
        return null;
    }
}
