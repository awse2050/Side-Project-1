package com.example.check.api.domains.todo.repository;

import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.web.v1.todo.dto.QTodoSearchDto;
import com.example.check.web.v1.todo.dto.TodoSearchDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.example.check.api.domains.todo.entity.QTodo.todo;

@Repository
@Log4j2
public class TodoQueryRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public TodoQueryRepository(JPAQueryFactory jpaQueryFactory) {
        super(Todo.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<TodoSearchDto> searchTodo(String searchContent) {

        List<TodoSearchDto> todos = jpaQueryFactory
                .select(new QTodoSearchDto(
                        todo.id,
                        todo.content,
                        todo.writer,
                        todo.checked
                )).from(todo)
                .where(isContainsContent(searchContent))
                .orderBy(todo.id.desc())
                .fetch();
        
        return todos;
    }

    private BooleanExpression isContainsContent(String searchContent) {
        return Objects.nonNull(searchContent) ? todo.content.containsIgnoreCase(searchContent) : null;
    }
}
