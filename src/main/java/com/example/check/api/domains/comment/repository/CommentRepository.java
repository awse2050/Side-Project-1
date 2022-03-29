package com.example.check.api.domains.comment.repository;

import com.example.check.api.domains.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
