package com.example.check.api.domains.comment.repository;

import com.example.check.api.domains.comment.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Modifying
    @Query("delete from Comment c where c.todo.id = :todoId")
    public void deleteByTodoId(@Param("todoId") Long todoId);
    /*
        Join Fetch VS @EntityGraph
        -> inner Join vs left Outer Join
     */
    @Query("SELECT c FROM Comment c join fetch c.todo t")
    public List<Comment> findAllWithTodoJoinFetch();

    @EntityGraph(attributePaths = "todo", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT c from Comment c")
    public List<Comment> findAllWithTodo();
}
