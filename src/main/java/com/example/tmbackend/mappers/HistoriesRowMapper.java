/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.mappers;

import com.example.tmbackend.models.History;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author yshah
 */
public class HistoriesRowMapper implements RowMapper<History>{
    @Override
    public History mapRow(ResultSet row, int rowNum) throws SQLException {
        History histories = new History();
        histories.setUserId(row.getInt("userId"));
        histories.setTaskId(row.getInt("taskId"));
        histories.setCreatedBy(row.getInt("createdBy"));
        histories.setCreatedAt(row.getDate("createdAt"));
        histories.setUpdatedBy(row.getInt("updatedBy"));
        histories.setUpdatedAt(row.getDate("updatedAt"));
        return histories;

    }
}
