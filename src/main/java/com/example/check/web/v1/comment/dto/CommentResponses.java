package com.example.check.web.v1.comment.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class CommentResponses {
    private Long commentId;
    private String commentContent;
    private String commentWriter;
    private LocalDateTime modDate;
}
