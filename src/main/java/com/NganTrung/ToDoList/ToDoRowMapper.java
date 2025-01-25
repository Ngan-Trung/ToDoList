package com.NganTrung.ToDoList;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDoRowMapper implements RowMapper<ToDo> {
    @Override
    public ToDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        ToDo toDo = new ToDo();
        toDo.setId(rs.getInt("id"));
        toDo.setTitle(rs.getString("title"));
        toDo.setContent(rs.getString("content"));
        toDo.setCompleted(rs.getBoolean("completed"));
        return toDo;
    }
}
