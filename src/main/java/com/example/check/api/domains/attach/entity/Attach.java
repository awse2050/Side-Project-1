package com.example.check.api.domains.attach.entity;

import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.api.util.entity.DateEntity;
import com.example.check.web.v1.attach.dto.AttachDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString(exclude = "todo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Attach extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTACH_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TODO_ID")
    private Todo todo;

    @Column(name = "UPLOAD_FILE_NAME", nullable = false)
    private String uploadFileName;

    @Column(name = "TO_SAVED_FILE_NAME", nullable = false)
    private String toSavedFileName;

    public Attach(String uploadFileName, String toSavedFileName) {
        this.uploadFileName = uploadFileName;
        this.toSavedFileName = toSavedFileName;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public AttachDto bindToAttachDto() {
        return new AttachDto(this.id, this.uploadFileName);
    }
}
