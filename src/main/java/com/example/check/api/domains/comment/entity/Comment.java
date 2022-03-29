package com.example.check.api.domains.comment.entity;

import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.api.util.entity.DateEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Getter
public class Comment extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer; // 작성자 -> Member

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TODO_ID")
    private Todo todo;

    public void changeContent(String content) {
        this.content = content;
    }

}
