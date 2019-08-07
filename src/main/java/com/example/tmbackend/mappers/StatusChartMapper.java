package com.example.tmbackend.mappers;

import com.example.tmbackend.models.Chart;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class StatusChartMapper implements RowMapper<Chart> {

    @Override
    public Chart mapRow(ResultSet row, int i) throws SQLException {
        Chart st = new Chart();
        st.setName(row.getString("status"));
        st.setCounter(row.getInt("counter"));
        return st;
    }

}
