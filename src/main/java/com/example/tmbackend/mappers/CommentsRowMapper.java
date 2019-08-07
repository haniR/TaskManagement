
package com.example.tmbackend.mappers;
import com.example.tmbackend.models.Comment;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CommentsRowMapper implements RowMapper<Comment>{
    
    @Override
    public Comment mapRow(ResultSet row, int rowNum) throws SQLException {
        Comment comments = new Comment();
        comments.setId(row.getInt("id"));
        comments.setDescription(row.getString("description"));
        comments.setTaskId(row.getInt("tasksId"));
        comments.setCreatedBy(row.getInt("createdBy"));
        comments.setCreatedAt(row.getDate("createdAt"));
        comments.setUpdatedBy(row.getInt("updatedBy"));
        comments.setUpdatedAt(row.getDate("updatedAt"));
        return comments;

    }
}
