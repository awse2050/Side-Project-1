package com.example.check.api.domains.attach.repository;

import com.example.check.api.domains.attach.entity.Attach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttachRepository extends JpaRepository<Attach, Long> {
    @Modifying
    @Query("delete from Attach a where a.todo.id = :todoId")
    public void deleteByTodoId(@Param("todoId") Long todoId);
}
