
package com.example.tmbackend.daos;

import com.example.tmbackend.mappers.CommentsRowMapper;
import com.example.tmbackend.models.Comment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class CommentsDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Comment> getComments() {
        String sql = "SELECT * FROM comments";
        RowMapper<Comment> rowMapper = new CommentsRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public Comment getCommentById(int id) {
        String sql = "SELECT * "
                + "from taskmanagement.comments "
                + "WHERE id = ?";
        RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<Comment>(Comment.class);
        Comment comments = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return comments;
    }

    public void addComment(Comment comment) {
        String sql = "INSERT INTO taskmanagement.comments ( description, tasksId,createdBy,createdAt,updatedBy,updatedAt) values (?,?,?,?,?, ?)";
        jdbcTemplate.update(sql, comment.getDescription(), comment.getTaskId()
                ,comment.getCreatedBy(),comment.getCreatedAt(),comment.getUpdatedBy(),comment.getUpdatedAt());

//        sql = "SELECT id FROM comments WHERE id =?";
//        int id = jdbcTemplate.queryForObject(sql, Integer.class, comment.getId());
        Comment sameComment = new Comment();
        sameComment = getLastCommentInDb();
        ////

        comment.setId(sameComment.getId());
        
    }

    public boolean updateComment(Comment comment) {
        String sql = "UPDATE taskmanagement.comments "
                + "SET description =?, tasksId =? ,createdBy = ?,createdAt = ?,updatedBy = ?,updatedAt = ?"
                + "WHERE id =?";
        return jdbcTemplate.update(sql, comment.getDescription(), comment.getTaskId(),
                comment.getCreatedBy(),comment.getCreatedAt(),comment.getUpdatedBy(),comment.getUpdatedBy(),
                                    comment.getId()) != 0;
    }

    public boolean deleteComment(int id) {
        String sql = "DELETE FROM taskmanagement.comments WHERE id=?";
        return jdbcTemplate.update(sql, id) != 0;
    }

    public boolean CommentExists(int id) {
        String sql = "SELECT count(*) "
                + "FROM taskmanagement.comments "
                + "WHERE id=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class,id);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public Comment getLastCommentInDb() {

        String sql = "select *from comments ORDER BY id DESC LIMIT 1";
        RowMapper<Comment> rowMapper = new CommentsRowMapper();

        return this.jdbcTemplate.queryForObject(sql, rowMapper);
    }
}
