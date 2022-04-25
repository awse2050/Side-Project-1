package com.example.check.api.domains.todo.service;

import com.example.check.api.domains.comment.entity.Comment;
import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.api.domains.todo.repository.TodoRepository;
import com.example.check.web.v1.todo.dto.TodoCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class TodoCreator {

    private final TodoRepository todoRepository;

    @Transactional
    public Long createTodo(TodoCreateDto createDto) {
        Todo entity = createDto.bindToEntity();

        return todoRepository.save(entity).getId();
    }

    /**
     *  N+1 테스트 데이터
     */
    @PostConstruct
    void BEFORE_CREATE_ENTITY() {
        for(int j=0; j<10; j++) {
            Todo entity = Todo.builder()
                    .content("todo"+j)
                    .writer("writer"+j)
                    .checked(false)
                    .build();
            for(int i=0; i<=5; i++) {
                entity.addComments(Comment.builder().content("안녕하세요."+i).writer("작성자"+i).todo(entity).build());
            }
            todoRepository.save(entity);
        }
    }
}
