package com.NganTrung.ToDoList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Dao {
    public ToDo getSingleToDo(int id);

    public List<ToDo> getAllToDo();

    public List<ToDo> getToDoByTitle(String title);

    public List<ToDo> getToDoByContent(String content);

    public List<ToDo> getCompletedToDo(boolean completed);

    public ToDo createToDo(ToDo toDo);

    public ToDo overrideToDo(ToDo toDo);

    public ToDo updateToDo(int id, Map<String, Object> data);

    public ToDo deleteToDo(int id);
}
