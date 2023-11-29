package com.example.TodoManagement.mapper;

import com.example.TodoManagement.dto.TodoDto;
import com.example.TodoManagement.entity.Todo;

public class TodoMapper {
    public static Todo mapToJpa(TodoDto todoDto) {
        Todo todo = new Todo(
                todoDto.getId(),
                todoDto.getTitle(),
                todoDto.getDescription(),
                todoDto.isCompleted()
        );
        return todo;
    }
    public static TodoDto mapToDto(Todo todo) {
        TodoDto todoDto = new TodoDto(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted()
        );
        return todoDto;
    }

}
