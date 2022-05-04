package com.example.check.api.domains.comment.entity;

import com.example.check.api.domains.member.entity.Member;
import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.api.util.entity.DateEntity;
import com.example.check.web.v1.comment.dto.CommentResponses;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
@ToString(exclude = "todo")
public class Comment extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TODO_ID")
    private Todo todo;

    public void changeContent(String content) {
        this.content = content;
    }

    public CommentResponses bindToDto() {
        return new CommentResponses(this.id,
                this.content,
                this.member.getName(),
                this.getModDate()
        );
    }

}
