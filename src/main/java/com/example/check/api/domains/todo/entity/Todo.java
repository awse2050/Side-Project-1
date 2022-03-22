package com.example.check.api.domains.todo.entity;

import com.example.check.api.util.converter.TodoCheckedConverter;
import com.example.check.api.util.entity.DateEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity
public class Todo extends DateEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_ID")
    private Long id;

    @Lob // text
    private String content; // 내용

    @Column(name = "MEMBER_ID")
    private String writer; // 작성자 -> Member

    @Convert(converter = TodoCheckedConverter.class)
    private boolean checked;

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeChecked() {
        this.checked = !checked;
    }
}
