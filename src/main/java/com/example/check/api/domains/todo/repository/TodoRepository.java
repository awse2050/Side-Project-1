package com.example.check.api.domains.todo.repository;

import com.example.check.api.domains.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Modifying
    @Query("delete from Todo t where t.id = :todoId")
    public void deleteByTodoId(@Param("todoId") Long todoId);
}
