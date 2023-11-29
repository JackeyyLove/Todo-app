package com.example.TodoManagement.service.impl;

import com.example.TodoManagement.dto.TodoDto;
import com.example.TodoManagement.entity.Todo;
import com.example.TodoManagement.exception.LoadingException;
import com.example.TodoManagement.mapper.TodoMapper;
import com.example.TodoManagement.repository.TodoRepository;
import com.example.TodoManagement.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        //convert ToDoDto into Tdo Jpa entity
        Todo todo = new Todo();
        todo = TodoMapper.mapToJpa(todoDto);

        // Todo Jpa entity
        Todo savedTodo = todoRepository.save(todo);
        // Convert saved Todo Jpa entity object into TodoDto object
        TodoDto savedTodoDto = new TodoDto();
        savedTodoDto = TodoMapper.mapToDto(todo);
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new LoadingException("Cannot find todo with id: " + id) );
        return TodoMapper.mapToDto(todo);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<TodoDto> todoDtoList = new ArrayList<>();
        List<Todo> todoList = todoRepository.findAll();
        todoList.forEach(todo -> {
            todoDtoList.add(TodoMapper.mapToDto(todo));
        });
        return todoDtoList;
    }

    @Override
    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new LoadingException("Cannot find todo with id: "+ id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.mapToDto(updatedTodo);

    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new LoadingException("Cannot find todo with id: " + id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new LoadingException("Cannot find todo with id: " + id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.mapToDto(updatedTodo);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new LoadingException("Cannot find todo with id: " + id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.mapToDto(updatedTodo);    }
}
