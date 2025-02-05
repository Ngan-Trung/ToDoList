package com.NganTrung.ToDoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ToDoService implements com.NganTrung.ToDoList.Service {
    @Autowired
    Dao dao;

    public ToDo getSingleToDo(int id){
        return dao.getSingleToDo(id);
    }

    public List<ToDo> getAllToDo(){
        return dao.getAllToDo();
    }

    public List<ToDo> getToDoByTitle(String title){
        return dao.getToDoByTitle(title);
    }

    public List<ToDo> getToDoByContent(String content){
        return dao.getToDoByContent(content);
    }

    public List<ToDo> getCompletedToDo(boolean completed){
        return dao.getCompletedToDo(completed);
    }

    @Transactional
    public ToDo createToDo(ToDo toDo){
        if(getSingleToDo(toDo.getId()) != null){
            return null;
        }
        return dao.createToDo(toDo);
    }

    @Transactional
    public ToDo overrideToDo(ToDo toDo){
        if(toDo == null){
            return null;
        }
        return dao.overrideToDo(toDo);
    }

    @Transactional
    public ToDo updateToDo(int id, Map<String, Object> map){
        return dao.updateToDo(id, map);
    }

    @Transactional
    public ToDo deleteToDo(int id){
        return dao.deleteToDo(id);
    }
}
