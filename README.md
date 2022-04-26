
### Checker  ( Last Update - 22.04.25 )

일정관리 & To-do List Practice Project

실습 목적성 & 사이드 프로젝트 

### Tech Stack

- Spring Boot 2.6.4

- Spring Data JPA

- H2 (Local)

- Spring Security (JWT)

- Attach ( S3로 변경 )

- Jenkins (진행)

### 관련 이슈

1. **벌크 삭제 및 Cascade, orphanRemoval 속성의 충돌.**

부모 `Entity` 를 삭제할 경우 자식 `Entity` 를 삭제해줘야 함.

`Cascade.REMOVE` 를 통해서 설정가능 → 대신 여러번의 `Delete SQL` 이 발생함.

여러번의 `SQL` 을 방지하기 위해서 자식 `Entity` 에는 **벌크삭제**를 이용

이 때, `Cascade` , `orphanRemoval` 의 공통옵션에 의해서 벌크삭제를 통한 `SQL` 과 부모 `Entity` 의 `deleteById` 에 의한 `Delete SQL` 이 충돌함.

따라서, 옵션을 제거하고 `Cascade.Persist`옵션만 설정해서 해결.

2. **Todo - Attach 의 `@OneToOne` 매핑 관련** 

실무에서는 `객체지향관점`으로 하는지, `DB관점`으로 하는지 모른다.

외래 키 설정을 위해서 어떤 관점으로 할 지 정해야 한다.

우선 혼자개발의 입장에서는 객체지향적으로 관리하는 선택이 맞을 수 있으나, 그룹일 경우에는 모르겠음.

양방향 매핑을 할 경우 `N+1` 을 해결해야 하지만, 객체지향적인 관점에서의 문제는 쉽게 해결되므로 `양방향` + `DB관점`으로 설계한다.
