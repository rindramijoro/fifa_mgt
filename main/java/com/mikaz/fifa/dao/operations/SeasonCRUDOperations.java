package com.mikaz.fifa.dao.operations;

import com.mikaz.fifa.dao.DbConnection;
import com.mikaz.fifa.dao.mapper.SeasonMapper;
import com.mikaz.fifa.model.Season;
import com.mikaz.fifa.model.Status;
import com.mikaz.fifa.service.exceptions.NotFoundException;
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

        return getAll(1,Integer.MAX_VALUE);
    }

    @Override
    public Season updateStatus(Integer seasonStart, Status newStatus){
        String selectSql = "select id_season, season_start, alias, season_status from season where season_start=?";
        String updateSql = "update season set season_status=? where season_start=?";

        try(Connection conn = dbConnection.getConnection();
            PreparedStatement selectPs = conn.prepareStatement(selectSql))
        {
            selectPs.setInt(1,seasonStart);
            ResultSet rs = selectPs.executeQuery();

            if(!rs.next()){
                throw new NotFoundException("Season not found");
            }

            Status currentStatus = Status.valueOf(rs.getString("season_status"));

            if(newStatus.ordinal() <= currentStatus.ordinal()){
                throw new IllegalArgumentException("Invalid status transition");
            }

            try(PreparedStatement updatePs = conn.prepareStatement(updateSql)){
                updatePs.setObject(1, newStatus.name(), java.sql.Types.OTHER);
                updatePs.setInt(2, seasonStart);
                updatePs.executeUpdate();
            }

            try (ResultSet rs2 = selectPs.executeQuery()) {
                if (rs2.next()) {
                    return seasonMapper.apply(rs2);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
