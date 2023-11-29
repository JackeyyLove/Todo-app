package com.example.TodoManagement.repository;

import com.example.TodoManagement.entity.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  TodoRepository  extends JpaRepository<Todo, Long> {
}
