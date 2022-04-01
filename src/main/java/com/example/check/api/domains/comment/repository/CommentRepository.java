package com.example.check.api.domains.comment.repository;

import com.example.check.api.domains.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Modifying
    @Query("delete from Comment c where c.todo.id = :todoId")
    public void deleteByTodoId(@Param("todoId") Long todoId);
}
