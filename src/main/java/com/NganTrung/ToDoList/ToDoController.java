package com.NganTrung.ToDoList;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Validated
public class ToDoController {
    @Autowired
    Service service;

    @GetMapping("/searchId/{id}")
    public ResponseEntity<ToDo> getSingleToDo(@PathVariable int id) {
        try {
            ToDo toDo = service.getSingleToDo(id);
            if (toDo == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok().body(toDo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ToDo>> getAllToDo() {
        try {
            List<ToDo> todos = service.getAllToDo();
            return ResponseEntity.ok(todos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/searchTitle/{title}")
    public ResponseEntity<List<ToDo>> getByTitle(@PathVariable @Size(max = 100) String title) {
        try {
            if (title == null || title.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            List<ToDo> todos = service.getToDoByTitle(title);
            return ResponseEntity.ok(todos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/content")
    public ResponseEntity<List<ToDo>> getByContent(@RequestBody  @Valid ToDo toDo) {
        try {
            if (toDo.getContent() == null || toDo.getContent().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            List<ToDo> todos = service.getToDoByContent(toDo.getContent());
            return ResponseEntity.ok(todos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/isCompleted/{completed}")
    public ResponseEntity<List<ToDo>> getByCompleted(@PathVariable boolean completed) {
        try {
            List<ToDo> todos = service.getCompletedToDo(completed);
            return ResponseEntity.ok(todos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ToDo> createToDo(@RequestBody  @Valid ToDo toDo) {
        try {
            if (toDo == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            ToDo createdToDo = service.createToDo(toDo);
            if (createdToDo == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(createdToDo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/override")
    public ResponseEntity<ToDo> overrideToDo(@RequestBody  @Valid ToDo toDo) {
        try {
            if (toDo == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            ToDo updatedToDo = service.overrideToDo(toDo);
            return ResponseEntity.ok(updatedToDo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable int id, @RequestBody Map<String, Object> map) {
        try {
            if (map.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            ToDo updatedToDo = service.updateToDo(id, map);
            if (updatedToDo == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(updatedToDo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ToDo> deleteToDo(@PathVariable int id) {
        try {
            ToDo toDo = service.deleteToDo(id);
            if (toDo == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(toDo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

