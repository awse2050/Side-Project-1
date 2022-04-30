package com.example.check.api.domains.todo.repository;

import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.web.v1.todo.dto.TodoSelectDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.check.api.domains.attach.entity.QAttach.attach;
import static com.example.check.api.domains.comment.entity.QComment.comment;
import static com.example.check.api.domains.todo.entity.QTodo.todo;

@Repository
public class TodoQueryRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public TodoQueryRepository(JPAQueryFactory jpaQueryFactory) {
        super(Todo.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Page<Todo> todosFindAllByPaging(Pageable pageable) {

        List<Todo> todos = jpaQueryFactory.select(todo)
                .from(todo)
                .leftJoin(todo.comments, comment).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(todo.id.asc())
                .fetch();

        return new PageImpl<>(todos, pageable, todos.size());
    }

    public List<TodoSelectDto> findTodos() {
        List<Todo> todos = jpaQueryFactory
                .selectFrom(todo).distinct()
                .leftJoin(todo.comments, comment).fetchJoin()
                .leftJoin(todo.attach, attach).fetchJoin()
                .orderBy(todo.id.asc())
                .fetch();

        return formatResult(todos);
    }

    public List<TodoSelectDto> searchTodo(String searchContent) {
        List<Todo> todos = jpaQueryFactory
                .selectFrom(todo).distinct()
                .leftJoin(todo.comments, comment)
                .where(isContainsContent(searchContent))
                .orderBy(todo.id.desc())
                .fetch();

        return formatResult(todos);
    }

    private List<TodoSelectDto> formatResult(List<Todo> todos) {
        return  todos.stream()
                .map(todo -> todo.bindToDto())
                .collect(Collectors.toList());
    }

    private BooleanExpression isContainsContent(String searchContent) {
        return Objects.nonNull(searchContent) ? todo.content.containsIgnoreCase(searchContent) : null;
    }
}
