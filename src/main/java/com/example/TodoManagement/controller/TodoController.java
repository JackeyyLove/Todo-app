package com.example.TodoManagement.controller;

import com.example.TodoManagement.dto.TodoDto;

import com.example.TodoManagement.entity.Todo;
import com.example.TodoManagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;
    // Build add todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodoDto = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    // Build get todo REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id) {
        TodoDto todoDto = todoService.getTodo(id);
        return ResponseEntity.ok(todoDto);
    }

    // Build get all todos REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping()
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todoDtoList = todoService.getAllTodos();
        return ResponseEntity.ok(todoDtoList);
    }

     //Build update todo REST API
     @PreAuthorize("hasRole('ADMIN')")
     @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long id, @RequestBody TodoDto todoDto) {
        TodoDto updatedTodoDto = todoService.updateTodo(id, todoDto);
        return ResponseEntity.ok(updatedTodoDto);
    }

    // Build delete todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("The task with id " + id + " has been deleted!");
    }

    // Build complete todo REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id) {
        TodoDto todoDto = todoService.completeTodo(id);
        return ResponseEntity.ok(todoDto);
    }

    // Build incomplete todo REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/{id}/in-complete")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable("id") Long id) {
        TodoDto todoDto = todoService.incompleteTodo(id);
        return ResponseEntity.ok(todoDto);
    }
}
