# 프로젝트 전환 (2020.12.20)

### 최신 개발 트렌드에 맞게 기존 [recipe](https://github.com/seongbinko/recipe) 프로젝트를 전환 하려고 한다.
1. 전자정부프레임워크 3.9 (spring 4.3.2) => 스프링부트로 전환
2. jQuery => vanilla Js 로 전환 ES6 문법 활용
3. Oracle => PostgreSql
4. Mybatis => JPA
5. Jsp => thymeleaf
6. 회원 가입,인증 JWT token
7. Java 8, 11 문법 사용
8. TDD 기반 개발
   - JUnit4 => JUnit5 전환 및 모든 api를 TDD 방식으로 변경한다.

## 개발 일지

### 2020.12.20

* recipe-v2 project 생성 (https://github.com/seongbinko/recipe-v2)
* thymeleaf index.html 생성
* controller 테스트 수행 ()
* postgreSql mybatis 연동

### 2020.01.02

* 기존의 master를 local_master branch로 변경
* local_01 branch를 master로 변경
* thymeleaf로 회원가입 뷰 생성 (bootstrap4 이용)
* 계정 관련 컨트롤러 생성 (스프링 시큐리티 적용) 및 테스트(Junit5)
* nav, footer 뷰 생성
* 로고 이미지 등록