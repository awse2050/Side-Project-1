
## Checker  ( Last Update - 22.05.23 )

일정관리 & To-do List Practice Project

`JPA`에서 발생할 수 있는 `N+1` 문제나 여러가지 상황에 대한 실습을 목적으로 만들었으며,

추가적으로 기용할 수 있는 기술 `JWT` 등의 다양한 기술들을 접목해보는 사이드 프로젝트

## Tech Stack

#### Spring Boot 2.6.4

기존의 `Spring` 프레임워크와 다르게 추가적인 설정(`SessionFactory, DataSource`) 들에 대한 개발자의 작성이 불필요해지고

그에 따라 개발자들이 개발이라는 역할에 집중할수 있게 도와줌으로써 선택. 또한 `jar` 파일을 통한 손쉬운 배포를 제공하기 때문.

#### Spring Data JPA

`JPA` 기술을 조금 더 쉽게 사용할 수 있도록 해준다. 기존의 `MyBatis` 와 같은 `SQL Mapper` 와는 다르게 객체지향관점에서

개발을 해야하기 때문에 `러닝커브`가 존재하는 편이지만, `SQL Mapper` 와 다르게 **문자열로 작성**을 하거나, **반복적인 쿼리 작성** 에 대한

부분에서 조금 더 안정적이고, 편리하게 개발할 수 있는 장점이 존재.

#### QueryDsl

`JPA` 기술을 사용하게 될 경우 복잡한 `쿼리` 나 `동적 쿼리` 에 대한 처리가 어렵기 때문에 이 부분에 대해서 

쉽게 처리할수 있도록 도와주는 기술로 **QueryDsl** 을 도입, 가장 큰 장점으로 `SQL Mapper` 들과 다르게 `JPQL` 이라는 언어의 형태인

**코드** 로 작성을 하기 때문에 **컴파일 시점** 에 오류를 미리 잡을 수 있다는 대표적인 특징이 있다.

#### H2 (Local)

`In-Memory DB`이며 워낙 가볍기 때문에 개발이나 테스트용도로 쓰기 좋다.

특히, 타 프로젝트에서 `Gradle build` 를 통한 `jar` 배포를 `CI/CD Tool` 에서 사용할 경우 `Test` 레벨에서의 예외로 인해

실패를 했던 경험이 있기 때문에, `Local` 환경에서 개발을 할 경우 주로 `H2` 를 사용하는 것을 선호한다.

#### Spring Security (JWT)

작은 프로젝트 일 경우 `Session`, `Cookie` 기반의 보안작업을 할 수 있는 `Spring Security` 를 통해 작업을 했으며,

이후 대규모 프로젝트로 넘어간다고 가정하거나, `MSA` 기반의 프로젝트로 전환할 경우 `세션 기반 인증` 을 이어가기에는 그 설정들이 복잡하기 때문에

`토근 기반 인증` 에 대한 도입으로 풀어나가는 것이 더 좋다고 판단하고 `JWT` 까지 도입을 시켰습니다.

#### Jenkins (Local Test)

대중적으로도 많이 쓰이는 `Jenkins` 를 활용했습니다. 실무에서는 `Local` 환경 뿐만 아니라 `dev`, `prod` 환경을 설정할 텐데,

그러한 분리된 환경에서도 조금 더 쉽게 `CI/CD` 를 할수 있게 `Pipeline` 을 지원하므로 추가적인 공부에 도움도 될 것이라고 판단하여 선택했습니다.

### 관련 이슈

1. [**벌크 삭제 및 Cascade, orphanRemoval 속성의 충돌**](https://awse2050.tistory.com/95?category=1007815)


2. **Todo - Attach 의 `@OneToOne` 매핑 관련** 

실무에서는 `객체지향관점`으로 하는지, `DB관점`으로 하는지 모른다.

외래 키 설정을 위해서 어떤 관점으로 할 지 정해야 한다.

우선 혼자개발의 입장에서는 객체지향적으로 관리하는 선택이 맞을 수 있으나, 그룹일 경우에는 모르겠음.

양방향 매핑을 할 경우 `N+1` 을 해결해야 하지만, 객체지향적인 관점에서의 문제는 쉽게 해결되므로 `양방향` + `DB관점`으로 설계한다.

3. [QueryDsl - Entity 조회시 연관관계 객체 수 만큼 Row 데이터 조회](https://awse2050.tistory.com/103)


4. JWT 인증 방식 이후 사용자의 정보를 사용해야할 경우 처리 방식 ( 문제발생 - 05.04 )

현재 `JWT Token` 을 `Header` 에서 가져온 후 `Token` 의 `payload` 에 담긴 `email` 을 가지고 

한번 더 존재하는 사용자인지 확인을 하고 있어 한번의 조회쿼리가 추가로 발생하고 있음.

```java
@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody @Validated TodoCreateDto createDto,
                                         @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {
        String requestEmail = JwtProvider.getSubject(jwtToken.replace("Bearer ", ""));
        Member findMember = memberDetailsService.findMember(requestEmail);

        Long id = todoCreator.createTodo(createDto, findMember);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
```

검색되는 블로그에서는 `SecurityContextHolder` 를 사용하고 있음. 

현재 사용하는 방식은 `SecurityContextHolder` 에 저장하지 않는 방식으로 사용 중이다.
