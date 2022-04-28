package com.example.check.api.domains.todo.repository;

import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.web.v1.comment.dto.CommentResponses;
import com.example.check.web.v1.todo.dto.QTodoSearchDto;
import com.example.check.web.v1.todo.dto.TodoSearchDto;
import com.example.check.web.v1.todo.dto.TodoSelectDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.check.api.domains.attach.entity.QAttach.attach;
import static com.example.check.api.domains.comment.entity.QComment.*;
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
                .leftJoin(todo.comments, comment).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(todo.id.asc())
                .fetch();

        return new PageImpl<>(todos, pageable, todos.size());
    }

    public  List<TodoSelectDto> todosFindAll() {
        List<Todo> todos = jpaQueryFactory.selectDistinct(todo)
                .from(todo)
                .leftJoin(todo.comments, comment).fetchJoin()
                .leftJoin(todo.attach, attach).fetchJoin()
                .orderBy(todo.id.asc())
                .fetch();

        /*
            역할에 대한 고민
         */
        List<TodoSelectDto> dtoList = todos.stream().map(todo -> {

            List<CommentResponses> commentCollections = new ArrayList<>();

            todo.getComments().forEach(commentEntity -> commentCollections.add(new CommentResponses(commentEntity)));

            return new TodoSelectDto(todo, commentCollections);
        }).collect(Collectors.toList());

        return dtoList;
    }

    /*
        DTO 를 통한 처리 방식.
        fetchJoin() 을 쓸수 없다.
        fetch join을 사용하는 이유는 엔티티 상태에서 엔티티 그래프를 참조하기 위해서 사용하는 것.
        따라서 당연히 엔티티가 아닌 DTO 상태로 조회하는 것은 불가능.
        그냥 join만 선언할 것.
     */
    public List<TodoSearchDto> searchTodo(String searchContent) {

        List<TodoSearchDto> todos = jpaQueryFactory
                .select(new QTodoSearchDto(
                        todo.id,
                        todo.content,
                        todo.writer,
                        todo.checked,
                        comment.id,
                        comment.content,
                        comment.writer,
                        comment.regDate
                ))
                .from(todo)
                .leftJoin(todo.comments, comment)
                .where(isContainsContent(searchContent))
                .orderBy(todo.id.desc())
                .fetch();
        
        return todos;
    }

    private BooleanExpression isContainsContent(String searchContent) {
        return Objects.nonNull(searchContent) ? todo.content.containsIgnoreCase(searchContent) : null;
    }
}
