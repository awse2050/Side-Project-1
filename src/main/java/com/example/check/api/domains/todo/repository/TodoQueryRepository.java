package com.example.check.api.domains.todo.repository;

import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.web.v1.todo.dto.QTodoSearchDto;
import com.example.check.web.v1.todo.dto.TodoSearchDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<Todo> todosFindAllByPaging(Pageable pageable) {

        List<Todo> todos = jpaQueryFactory.select(todo)
                .from(todo)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(todo.id.asc())
                .fetch();

        return new PageImpl<>(todos, pageable, todos.size());
    }

    public List<Todo> todosFindAll() {
        List<Todo> todos = jpaQueryFactory.select(todo)
                .from(todo)
                .orderBy(todo.id.asc())
                .fetch();

        return todos;
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
