/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.daos;

import com.example.tmbackend.mappers.ProjectRowMapper;
import com.example.tmbackend.mappers.TasksRowMapper;
import com.example.tmbackend.mappers.UsersRowMapper;
import com.example.tmbackend.models.Project;
import com.example.tmbackend.models.Task;
import com.example.tmbackend.models.User;
import com.example.tmbackend.models.User_projects;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class UsersDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getLastUserInDb() {

        String sql = "select *from users ORDER BY id DESC LIMIT 1";
        RowMapper<User> rowMapper = new UsersRowMapper();

        return this.jdbcTemplate.queryForObject(sql, rowMapper);
    }
    public List<User> getUsers() {
        String sql = "SELECT * FROM taskmanagement.users";
//String sql = "SELECT * FROM taskmanagerssystem.users";
        RowMapper<User> rowMapper = new UsersRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }
    public User getUserById(int id) {

        String sql = "SELECT * FROM taskmanagement.users as u where u.id=? ";
//        String sql = "SELECT * FROM taskmanagerssystem.users as u where u.id=? ";

        RowMapper<User> rowMapper = new UsersRowMapper();

        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
    public void addUserToProject(User_projects user) {
        String sql = "insert  into taskmanagement.user_projects (usersId,projectsId,createdBy,createdAt,updatedBy,updatedAt) values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getUsersId(), user.getProjectsId(), user.getCreatedBy(), user.getCreatedAt(), user.getUpdatedBy(), user.getUpdatedAt());
    }
    public List<User> getAllUsersExist(int projectId) {

        RowMapper<User> rowMapper = new UsersRowMapper();
        String sql2 = "SELECT  u.*  from   users as u  where u.id not in "
                + "( SELECT  u.id  from   users as u ,user_projects as p  where u.id = p.usersId and p.projectsId= ?) ";

        return this.jdbcTemplate.query(sql2, rowMapper, projectId);
    }
    public List<User> getAllAdmins() {

        RowMapper<User> rowMapper = new UsersRowMapper();
        String sql2 = "SELECT  *  from   users as u where u.type = 'admin' ";

        return this.jdbcTemplate.query(sql2, rowMapper);
    }
    public List<Task> getTasksByUserId(int id) {
        RowMapper<Task> rowMapper = new TasksRowMapper();
        String sql = "SELECT t.*,u.name as user_name, p.name as project_name "
                + "from  tasks as t,users as u ,projects as p "
                + "where u.id= t.assigne and p.id = t.projectsId and u.id =?";
        return this.jdbcTemplate.query(sql, rowMapper, id);  
       }
      public List<Project> getProjectsByUserId(int id) {

        RowMapper<Project> rowMapper = new ProjectRowMapper();
        String sql2 = "SELECT p.*  , u2.name as AdminName from  projects as p , users as u ,users as u2, user_projects as up "
                + "where u.id= up.usersId and u2.id= p.adminId and p.id = up.projectsId and u.id = ?";

        return this.jdbcTemplate.query(sql2, rowMapper, id);
    }
    public List<User> getUsersByProjectId(int projectsId) {

        RowMapper<User> rowMapper = new UsersRowMapper();
        String sql2 = "SELECT u.*,p.*,u.name As name  from  projects as p , users as u , user_projects as up where u.id= up.usersId  and p.id = up.projectsId and up.projectsId =?";

        return this.jdbcTemplate.query(sql2, rowMapper, projectsId);
    }
    public User getUserLoginId(User users) {
        String sql = "SELECT * FROM taskmanagement.users where username like ? and password like ?";
//String sql = "SELECT * FROM taskmanagerssystem.users where username like ? and password like ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        User user = jdbcTemplate.queryForObject(sql, rowMapper, users.getUserName(), users.getPassword());
        return user;
    }
    public void addUser(User user) throws IOException {
        String s = user.getSrc();
   
        String sql = "INSERT INTO taskmanagement.users ( name,password,userName,dateOfBirth,type,src,createdBy,createdAt,updatedBy,updatedAt) "
                + "values (?,?,?,?,?,?,?,?,?,?)";
//         String sql = "INSERT INTO taskmanagerssystem.users ( name,password,userName,dateOfBirth,type,src,createdBy,createdAt,updatedBy,updatedAt) "
//                + "values (?,?,?,?,?,?,?,?,?,?)";

        Date date = new Date(System.currentTimeMillis());
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        user.setCreatedBy(-1);
        user.setUpdatedBy(-1);

        /////
        jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getUserName(),
                user.getDateOfBirth(), user.getType(), user.getSrc(), user.getCreatedBy(), user.getCreatedAt(), user.getUpdatedBy(), user.getUpdatedAt());
        ////////////////////
        User sameUser = new User();
        sameUser = getLastUserInDb();
        ////

        user.setId(sameUser.getId());
        //////
        String path = imageSource(sameUser.getId(), s);
        System.out.println("<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(path);
        System.out.println("<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>");


        user.setUpdatedBy(sameUser.getId());
        user.setSrc("img" + sameUser.getId() + path);
        user.setCreatedBy(sameUser.getId());
        boolean a = updateUser(user);
    }

    public boolean updateUser(User user) throws IOException {

        String sql = "UPDATE taskmanagement.users SET name =?, password =?, userName =?"
                + ", dateOfBirth =?, type =?, src =? ,createdBy =?,createdAt = ?,updatedBy =?,updatedAt =?"
                + " WHERE id =?";
        User oldUser = new User();
        oldUser = getUserById(user.getId());
        user.setCreatedAt(oldUser.getCreatedAt());
        user.setPassword(oldUser.getPassword());
        user.setCreatedBy(oldUser.getCreatedBy());

        Date date = new Date(System.currentTimeMillis());
        user.setUpdatedAt(date);

        if (user.getSrc() == "") {
            user.setSrc(oldUser.getSrc());

        } else {
            deleteImageSource(oldUser.getId());
            String s = imageSource(user.getId(), user.getSrc());
            user.setSrc(oldUser.getSrc()+"."+s);
        }

        return jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getUserName(),
                user.getDateOfBirth(), user.getType(), user.getSrc(), user.getCreatedBy(),
                user.getCreatedAt(), user.getUpdatedBy(), user.getUpdatedAt(), user.getId()) != 0;
    }

    public boolean updateUserForName(User user) throws IOException {

        String sql = "UPDATE taskmanagement.users SET name =?,updatedBy =?,updatedAt =?"
                + " WHERE id =?";
        return jdbcTemplate.update(sql, user.getName(), user.getCreatedBy(), user.getCreatedAt(), user.getId()) != 0;

    }

    public boolean deleteUser(int id) {
        String sql = "DELETE FROM taskmanagement.users WHERE id=?";
//        String sql = "DELETE FROM taskmanagerssystem.users WHERE id=?";

        return jdbcTemplate.update(sql, id) != 0;
    }

    public boolean deleteUserFromProject(int from, int to, int projectId) {
        String sql = "UPDATE taskmanagement.tasks SET assigne =?,updatedAt=? WHERE assigne =?";
        jdbcTemplate.update(sql, to, new java.util.Date(), from);
        String sql2 = "DELETE FROM taskmanagement.user_projects WHERE usersId=? and projectsId = ?";
        return jdbcTemplate.update(sql2, from, projectId) != 0;
    }

    public boolean UserExists(int id) {
        String sql = "SELECT count(*) FROM taskmanagement.users "
                + "WHERE id =?";
//        String sql = "SELECT count(*) FROM taskmanagerssystem.users "
//                + "WHERE id =?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM taskmanagement.users where username =? and password =? ";

        RowMapper<User> rowMapper = new UsersRowMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper, username, password);
    }

    public String imageSource(int id, String file) throws IOException {

        String base64Image = file.split(",")[1];
         String type = file.split("/")[1].split(";")[0];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
        File f = new File("");
        String path = f.getAbsolutePath();
        String full = path.substring(0, path.lastIndexOf("\\")) + "\\webapps\\pics\\img" + id + "";
        File outputfile = new File(full);
        ImageIO.write(img, type, outputfile);

        return type;
    }

    public void deleteImageSource(int id) {
        //DELETE ELEMENT FROM FOLDER
        File f = new File("");
        String path = f.getAbsolutePath();
        String fullPath = path.substring(0, path.lastIndexOf("\\")) + "\\webapps\\pics\\img" + id + "";

        Path imagesPath = Paths.get(fullPath);

        try {
            Files.delete(imagesPath);
            System.out.println("File "
                    + imagesPath.toAbsolutePath().toString()
                    + " successfully removed");
        } catch (IOException e) {
            System.err.println("Unable to delete "
                    + imagesPath.toAbsolutePath().toString()
                    + " due to...");
            e.printStackTrace();
        }
    }

   

}
