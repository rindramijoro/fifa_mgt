package com.mikaz.fifa.dao.operations;

import com.mikaz.fifa.dao.DbConnection;
import com.mikaz.fifa.dao.mapper.CoachMapper;
import com.mikaz.fifa.dao.mapper.SeasonMapper;
import com.mikaz.fifa.model.Season;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SeasonCRUDOperations implements CRUDOperations<Season> {

    private final DbConnection dbConnection;
    private final SeasonMapper seasonMapper;

    public SeasonCRUDOperations(DbConnection dbConnection, SeasonMapper seasonMapper) {
        this.dbConnection = dbConnection;
        this.seasonMapper = seasonMapper;
    }

    @Override
    public List<Season> getAll(Integer page, Integer size) {
        int defaultPage = (page != null) ? page : 1;
        int defaultSize = (size != null) ? size : 10;

        List<Season> seasons = new ArrayList<>();
        String sql = "select id_season, season_start, alias, season_status from season limit ? offset ?";
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setInt(1,defaultSize);
            ps.setInt(2,(defaultPage - 1) * defaultSize);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                seasons.add(seasonMapper.apply(rs));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return seasons;
    }

    @Override
    public List<Season> saveAll(List<Season> entities) {
        String sql = """
                insert into season(season_start, alias)
                values (?,?)
                """;
        List<Season> seasons = new ArrayList<>();
        try(Connection conn = dbConnection.getConnection();
           PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            for(Season season : entities){
                ps.setInt(1,season.getSeasonStart());
                ps.setString(2,season.getAlias());
                ps.addBatch();
            }

            ps.executeBatch();

            try(ResultSet rs = ps.getGeneratedKeys()){
                while(rs.next()){
                    seasons.add(seasonMapper.apply(rs));
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return seasons;
    }
}
