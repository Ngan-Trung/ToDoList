package com.NganTrung.ToDoList;

import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

@Component
public class ToDo {
    @PositiveOrZero
    int id;

    @NotBlank
    @Size(max = 100)
    String title;

    @Size(max = 1500)
    String content;

    boolean completed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
