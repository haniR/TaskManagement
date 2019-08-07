/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.mappers;

import com.example.tmbackend.models.Chart;
import com.example.tmbackend.models.PriorityChart;
import com.example.tmbackend.models.TypeChart;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ASUS
 */
public class TypeChartMapper implements RowMapper<Chart> {

    @Override
    public Chart mapRow(ResultSet row, int i) throws SQLException {
        Chart typeChart = new Chart();
        typeChart.setName(row.getString("type"));
        typeChart.setCounter(row.getInt("counter"));

        return typeChart;
    }

}
