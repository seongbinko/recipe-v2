[![Build Status](https://travis-ci.org/seongbinko/recipe-v2.svg?branch=master)](https://travis-ci.org/seongbinko/recipe-v2)

# Recipe-v2 프로젝트 (2020.12.20)

## 개요
- 명칭 : RECIPE
- 개발 인원 : 1명
- 담당 역할 :  주제선정, 분석/설계, 인프라 구축 및 레이아웃, 개발, 테스트, 시연
- 주요 기능 : 레시피 등록 조회 수정 삭제, 스크랩, 레시피 댓글 답글 대댓글(계층형 구조), 더미데이터를 크롤링 방식으로 수집, AWS인프라로 서비스 무중단 배포 
- 개발 환경 : Springboot 2.4.1, Spring Security, Jsp, Mybatis , Aws, Nginx 
- 데이터베이스 : MariaDB 10.5
- 형상관리: git
- 간단 소개 : 개인들의 레시피를 공유하는 커뮤니티
- 사이트 : [레시피 공유 커뮤니티](http://ec2-3-36-243-80.ap-northeast-2.compute.amazonaws.com/)
- 시연 영상: [유투브 링크](https://youtu.be/S1_22_-wCho)

<details>
<summary>Recipe(restful api) 프로젝트 개요(v1)</summary>
<div markdown="1">       

# 레시피 공유 커뮤니티 (with crawling)
![screencapture-localhost-8090-2020-07-18-12_56_54](https://user-images.githubusercontent.com/60464424/87844224-3b435580-c8f6-11ea-9c44-dfd6d72b4d08.png)
## 프로젝트 특징
- 전자정부표준프레임워크에 대한 이해와 이를 이용한 웹 어플리케이션 구현
- RESTful Api
- 더미 데이터를 직접 등록이 아닌 크롤링을 통하여 DB에 등록 (https://github.com/seongbinko/recipe/tree/master/src/main/java/kr/co/edsk/recipe/crawler)
## 개요
- 명칭 : RECIPE
- 개발 인원 : 1명
- 개발 기간 :  2020.05.11 ~ 2020. 06. 25 (35일)
- 담당 역할 :  주제선정, 분석/설계, 인프라 구축 및 레이아웃, 개발, 테스트, 시연
- 주요 기능 : 레시피 등록 조회 수정 삭제, 스크랩, 레시피 댓글 답글 대댓글(계층형 구조), 더미데이터를 크롤링 방식으로 수집
- 개발 환경 : 전자정부프레임워크 3.9
- 데이터베이스 : ORACLE 11g
- 웹 어플리케이션 서버 : Apache Tomcat 9.0
- 형상관리 툴 : SVN
- 간단 소개 : 개인들의 레시피를 공유하는 커뮤니티

## [프로젝트 계획서.doc](https://github.com/seongbinko/recipe/raw/master/%EA%B0%9C%EB%B0%9C%EB%AC%B8%EC%84%9C/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8_%EA%B3%84%ED%9A%8D%EC%84%9C.doc)
## [프로젝트 설계서.pptx](https://github.com/seongbinko/recipe/raw/master/%EA%B0%9C%EB%B0%9C%EB%AC%B8%EC%84%9C/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8_%EC%84%A4%EA%B3%84%EC%84%9C.pptx)

</div>
</details>

## 목표
1. Recipe 프로젝트 배포 및 서비스 운영
2. 운영 환경에 맞게 프로젝트 전환 및 기능 추가
<details>
<summary>초기 프로젝트 목표</summary>
<div markdown="1">

### 최신 개발 트렌드에 맞게 기존 [recipe](https://github.com/seongbinko/recipe) 프로젝트를 전환 하려고 한다.
1. 전자정부프레임워크 3.9 (spring 4.3.2) => 스프링부트로(2.4.1) 전환
2. jQuery => vanilla Js 로 전환 ES6 문법 활용
3. Oracle => PostreSQL
4. Mybatis => JPA
5. Jsp => thymeleaf
6. 회원 가입,인증 JWT token
7. Java 8, 11 문법 사용
8. TDD 기반 개발
   - JUnit4 => JUnit5 전환 및 모든 api를 TDD 방식으로 변경한다.
</div>
</details>

## 목표 조정 및 추가 사항
1. 전자정부프레임워크 3.9 (spring 4.3.2) => 스프링부트로(2.4.1) 전환
2. Oracle => Mariadb(10.5.8)로 변경
3. 회원 가입,로그인 기능 구현 (스프링 시큐리티 적용)
4. Java 8, 11 문법 사용 및 lombok 적용 
5. TDD 기반 개발 *-앞으로 추가되는 기능에 한하여 진행*
6. git으로 형상관리

## 개발 일지

### 2020.12.20

* recipe-v2 project 생성 (https://github.com/seongbinko/recipe-v2)
* thymeleaf index.html 생성
* controller 테스트 수행 ()
* postgreSql mybatis 연동

### 2021.01.02

* 기존의 master를 local_master branch로 변경
* local_01 branch를 master로 변경
* thymeleaf로 회원가입 뷰 생성 (bootstrap4 이용)
* 계정 관련 컨트롤러 생성 (스프링 시큐리티 적용) 및 테스트(Junit5)
* nav, footer 뷰 생성
* 로고 이미지 등록

### 2021.02.14

기존의 프로젝트 버전을 최신트렌드로 변경하는 것도 의미가 있지만 나의 서비스를 배포 운영하는 것으로 개발 방향을 바꾸어서 프로젝트를 진행하려 한다.
그에 따라 프로젝트 계획을 일부 수정한다.

1. 전자정부프레임워크 3.9 (spring 4.3.2) => 스프링부트로(2.4.1) 전환
2. jQuery => ~~vanilla Js 로 전환 ES6 문법 활용~~ *-유지*
3. Oracle => ~~PostreSQL~~ *-Mariadb로 변경*
4. Mybatis => ~~JPA~~ *-유지*
5. Jsp => ~~thymeleaf~~ *-유지*
6. 회원 가입,로그인 기능 구현 ~~JWT token~~ *-폼 인증 방식으로 변경 (스프링 시큐리티)*
7. Java 8, 11 문법 사용 및 lombok적
8. TDD 기반 개발
   - JUnit4 => JUnit5 전환 및 ~~모든~~ api를 TDD 방식으로 변경한다. *-앞으로 추가되는 기능에 한하여 진행*

#### 수정사항
1. recipe project 원 소스파일 적용 [recipe origin project](https://github.com/seongbinko/recipe) 
2. application.properties 수정
   - mariadb 설정 변경
   - 이미지 파일 저장 디렉토리 수정
   - jsp 설정 추가
3. 뷰가 thymeleaf에서 jsp로 변경됨에 따라 pom.xml 수정 및 파일 디렉토리 수정
4. lombok 적용 (dto, vo 클래스)
5. Oracle에서 Mariadb로 바뀜에 따라 mappers/*.xml 파일 쿼리 변경 (계층형 쿼리, 분석함수(row_number()), 조인, 시퀀스 등 )
6. 테이블 생성 및 데이터타입 변경
   - RECIPE_USER 테이블의 PHONE_NUMBER 컬럼 삭제 및 EMAIL 컬럼 추가 (이메일 인증을 위함)
   
### 2021.02.18
1. 전자정부프레임워크 관련 디렉토리, 파일 제거
2. RECIPE_USER 테이블 컬럼 추가 (USER_EMAIL_TOKEN, USER_EMAIL_VERIFIED)
3. 스프링 시큐리티 적용
4. 회원가입 기능 추가
   - 프론트단 유효성 검사
   - spring validation 적용
   - 패스워드 인코더 사용 ()
   - 회원가입시 이메일 인증을 해야 사이트 이용하도록 구현 (토큰 활용)
   - footer.html 카피라이트 추가
   - TDD 방식으로 개발 (Junit 5)
   
### 2021.02.28
1. 회원가입 기능 추가
   - 이메일 인증을 해야지만 서비스 가입 완료 및 이용가능하도록 변경
   - 악의적인 이메일 전송을 막기 위해서 1시간에 한번만 인증메일 전송가능 하도록 구현
   - 패스워드를 잊어버릴시 이메일로만 로그인 가능한 페이지를 제공하며, 이메일에서 url을 통해 접속시 패스워드 변경 페이지로 이동하여 패스워드 변경 가능하도록 구현
   - spring validation 적용
   - TDD 방식으로 개발 (Junit 5)
   
2. 프로필 기능 추가 
   - 로그인이 되면 프로필 페이지가 조회가능 하며 기본 프로필, 
   - 회원이 되면 프로필 수정 페이지에서 프로필 수정이 가능하며, 기본 정보 수정, 패스워드 변경, 닉네임 변경 등이 가능하다
   - spring validation 적용
   - TDD 방식으로 개발 (Junit 5)
   
3. application.properties 분리
   - local에서 필요한 것과 dev에서 필요한 것이 분리 되게 변경하였다. (ex 이메일 인증)
   
4. 예외 처리 페이지 구현
   - 스프링 디폴트 에러페이지에서 커스텀 페이지로 변경
   
5. 로고 수정 및 기본 아이콘 이미지 변경
   
6. 버그 수정
   - 계층형 댓글 기능이 동작하지 않아 쿼리 수정
   - 레시피 작성일이 나오지 않는 문제 수정
   - 모든 html 타이틀을 "레시피"로 수정
   - 기존의 소스 코드 일부 수정
   - 모든 테스트를 실행시 전부 통과하도록 변경
   
7. 패키기 구조 정리
   
TODO List
   - 프로필에서 내 게시글, 스크랩한 것을 볼 수 있도록 구현
   - 다른 사용자도 내 닉네임을 클릭시 프로필을 조회 할 수 있도록 구현
   - 파일업로드 용량 제한
   - 패키지 정리

### 2021.03.19

1. 프로젝트 파일 war로 배포 및 동작 확인
   - jsp는 스프링부트에서 jar로 배포할 시 동작하지 않기 때문
   
### 2021.04.01
1. 프로젝트 버그 수정
   - 페이징 처리 limit (endIndex) 변경
   - xml sql from 절 대문자로 변경
   
2. aws rds mariadb 10.5 데이터베이스 생성
   - 테이블 생성
   - mariadb 테이블 기본값 UTF-8로 변경
   
3. aws ec2 인스턴스 생성
   - kst로 로컬타임 변경
   - 80 포트 8080포트로 포워딩
   - jdk 11 설치
   - nohup으로 서버 실행
   - http://ec2-3-36-243-80.ap-northeast-2.compute.amazonaws.com
   
- Todo
   - 리눅스 환경에서 파일 업로드 된 이미지 불러오기
   - 도메인 구매

### 2021.04.17

- application-dev.properties 보안을 위해 삭제
- application.auth.properties는 깃허브에 올리지 않고 따로 보관
- 레시피 공유 사이트 url 수정

### 2021.04.19

- 무중단 배포 구현
   - github → trivis CI → aws s3 → code deploy → ec2  ← Nginx (8081, 8082)  ← client
      - .travis.yml 추가
      - appspec.yml 추가 (codedeploy 부분)
      - 무중단 배포를 위한 profileController 추가
      - application-real.properties 추가
      - 무중단 배포를 위한 스크립트 추가
         - stop.sh, start.sh, health.sh, switch.sh, profile.sh
      - appspec.yml 무중단 배포를 위한 hooks 변경
   - Ec2 이전으로 application.properties 메일서비스 url 변경
   
- recipe_insert.jsp (레시피 등록 기능 disabled 처리)

### 2021.05.12

- License 추가
   
### Todo

- 이미지 업로드 EC2 업로드 방식에서 AWS S3 업로드 방식으로 변경
