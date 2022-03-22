package com.example.check.api.domains.todo.repository;

import com.example.check.api.domains.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
