package com.NganTrung.ToDoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.*;

@Repository
public class ToDoDAO implements Dao{
    @Autowired
    NamedParameterJdbcTemplate jdbc;

    public ToDo getSingleToDo(int id){
        String sql = "SELECT * FROM ToDoList WHERE id = :id;";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        List<ToDo> list = jdbc.query(sql, map, new ToDoRowMapper());
        if(!list.isEmpty()){
           return list.get(0);
        }
        return null;
    }

    public List<ToDo> getAllToDo(){
        String sql = "SELECT * FROM ToDoList;";
        return jdbc.query(sql, new HashMap<>(), new ToDoRowMapper());
    }

    public List<ToDo> getToDoByTitle(String title){
        if(title == null || title.isEmpty()){
            return null;
        }
        String sql = "SELECT * FROM ToDoList WHERE title LIKE :title;";
        Map<String, Object> map = new HashMap<>();
        map.put("title", "%"+title+"%");
        return jdbc.query(sql, map, new ToDoRowMapper());
    }

    public List<ToDo> getToDoByContent(String content){
        if(content == null || content.isEmpty()){
            return null;
        }
        String sql = "SELECT * FROM ToDoList WHERE content LIKE :content;";
        Map<String, Object> map = new HashMap<>();
        map.put("content", "%"+content+"%");
        return jdbc.query(sql, map, new ToDoRowMapper());
    }

    public List<ToDo> getCompletedToDo(boolean completed){
        String sql = "SELECT * FROM ToDoList WHERE completed = :completed;";
        Map<String, Object> map = new HashMap<>();
        map.put("completed", completed);
        return jdbc.query(sql, map, new ToDoRowMapper());
    }

    public ToDo createToDo(ToDo toDo){
        if(getSingleToDo(toDo.getId()) != null){
            return null;
        }
        String sql = "INSERT INTO ToDoList (id, title, content, completed)" +
                "VALUES (:id, :title, :content, :completed);";

        Map<String, Object> map = new HashMap<>();
        map.put("id", toDo.getId());
        map.put("title", toDo.getTitle());
        map.put("content", toDo.getContent());
        map.put("completed", toDo.isCompleted());

        jdbc.update(sql, map);
        return toDo;
    }

    public ToDo overrideToDo(ToDo toDo){
        String sql = "UPDATE ToDoList SET title = :title, content = :content, " +
                "completed = :completed WHERE id = :id;";
        Map<String, Object> map = new HashMap<>();
        map.put("id", toDo.getId());
        map.put("title", toDo.getTitle());
        map.put("content", toDo.getContent());
        map.put("completed", toDo.isCompleted());

        jdbc.update(sql, map);
        return toDo;
    }

    public ToDo updateToDo(int id, Map<String, Object> data){
        if(getSingleToDo(id) == null || data.isEmpty()){
            return null;
        }

        String sql = "UPDATE ToDoList SET";
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        boolean addComma = false;

        if(data.containsKey("title")){
             sql += " title = :title ";
             addComma = true;
             map.put("title", data.get("title"));
        }
        if(data.containsKey("content")){
            if(addComma){
                sql += ", ";
            }
            sql += " content = :content ";
            addComma = true;
            map.put("content", data.get("content"));
        }
        if(data.containsKey("completed")){
            if(addComma){
                sql += ", ";
            }
            sql += " completed = :completed ";
            map.put("completed", data.get("completed"));
        }
        sql += " WHERE id = :id;";
        jdbc.update(sql, map);
        return getSingleToDo(id);
    }

    public ToDo deleteToDo(int id){
        ToDo toDo = getSingleToDo(id);
        if(toDo == null){
            return null;
        }
        String sql = "DELETE FROM ToDoList WHERE id = :id;";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        jdbc.update(sql, map);
        return toDo;
    }
}
