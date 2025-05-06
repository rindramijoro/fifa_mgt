package com.mikaz.fifa.dao.operations;

import com.mikaz.fifa.dao.DbConnection;
import com.mikaz.fifa.dao.mapper.ClubStatsMapper;
import com.mikaz.fifa.model.ClubStats;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClubStatsCRUDOperations implements CRUDOperations<ClubStats>{
    private final DbConnection dbConnection;
    private final ClubStatsMapper clubStatsMapper;

    public ClubStatsCRUDOperations(DbConnection dbConnection, ClubStatsMapper clubStatsMapper) {
        this.dbConnection = dbConnection;
        this.clubStatsMapper = clubStatsMapper;
    }

    @Override
    public List<ClubStats> getAll(Integer page, Integer size) {
        return List.of();
    }

    @Override
    public List<ClubStats> saveAll(List<ClubStats> entities) {
        return List.of();
    }

    public  List<ClubStats> getBySeasonYear(Integer seasonStart){
        List<ClubStats> clubStatsList = new ArrayList<>();
        String sql = """
                select cs.id_club_stats, cs.club, cs.scored, cs.against, cs.goal_difference,
                cs.clean_sheets, cs.points, cs.season from club_stats cs join season s
                on cs.season = s.id_season where season_start = ? order by cs.points desc
                """;
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1,seasonStart);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                clubStatsList.add(clubStatsMapper.apply(rs));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return clubStatsList;
    }
}
