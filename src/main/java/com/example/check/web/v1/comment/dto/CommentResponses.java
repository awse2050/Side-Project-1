package com.example.check.web.v1.comment.dto;

import com.example.check.api.domains.comment.entity.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CommentResponses {

    private Long commentId;
    private String commentContent;
    private String commentWriter;
    private LocalDateTime modDate;

    public CommentResponses(Comment comment) {
        this.commentId = comment.getId();
        this.commentContent = comment.getContent();
        this.commentWriter = comment.getWriter();
        this.modDate = comment.getModDate();
    }

}
