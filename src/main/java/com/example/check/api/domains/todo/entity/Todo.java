package com.example.check.api.domains.todo.entity;

import com.example.check.api.domains.attach.entity.Attach;
import com.example.check.api.domains.comment.entity.Comment;
import com.example.check.api.domains.member.entity.Member;
import com.example.check.api.util.converter.TodoCheckedConverter;
import com.example.check.api.util.entity.DateEntity;
import com.example.check.web.v1.todo.dto.TodoDto;
import com.example.check.web.v1.todo.dto.TodoSelectDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = {"attach", "comments"})
@Entity
public class Todo extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_ID")
    private Long id;

    @Column(nullable = false) // text
    private String content; // 내용

    @Column(name = "MEMBER_ID", nullable = false)
    private String writer;

    @Convert(converter = TodoCheckedConverter.class)
    private boolean checked;

    @OneToOne(mappedBy = "todo", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Attach attach;

    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    // 테스트전용 - 실제 API에서는 사용하지 않는다. 삭제예정
    public Todo(Attach attach) {
        this.attach = attach;
        this.content = "default content1";
        this.writer = "default writer1";
        attach.setTodo(this);
    }
    // 삭제예정
    public Todo(Attach attach, Member member) {
        this.attach = attach;
        this.content = "default content1";
        this.writer = member.getName();
        attach.setTodo(this);
    }

    public void addComments(Comment comment) {
        this.comments.add(comment);
    }
    // 테스트전용 - 실제 API에서는 사용하지 않는다.
    public void addAttach(Attach attach) {
        this.attach = attach;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeChecked() {
        this.checked = !checked;
    }

    public TodoSelectDto bindToDto() {
        return new TodoSelectDto(this, this.comments, this.attach);
    }

    public TodoDto bindToTodoDto() {
        return new TodoDto(this.id, this.content, this.writer, this.checked);
    }
}
