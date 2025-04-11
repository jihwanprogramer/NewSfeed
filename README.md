<img src="https://github.com/jihwanprogramer/TodoList/blob/main/ReadmeImage/todo.png?raw=true" alt="배너" width="100%"/>

<br/>
<br/>

# 1. Project Overview (프로젝트 개요)

- 프로젝트 이름: FACKBOOK
- 프로젝트 설명: Spring 프레임워크 와 JPA 를 이용하고 Session과 Filter를 이용하여 SNS NewsFeed만들어 보기

## API 명세서



## ERD



# 2. Key Features (주요 기능)

- **사용자 등록**
    - 사용자는 새로운 계정을 생성할 수 있으며, 이메일과 비밀번호를 입력하여 등록함.


- **사용자 로그인**
    - 사용자는 등록된 이메일과 비밀번호로 로그인할 수 있으며, 로그인 성공 시 사용자 정보를 반환함.


- **사용자 정보 조회**
    - 사용자는 자신의 ID로 정보 조회가 가능하며, UserResponseDto 형식으로 사용자 정보를 제공함.


- **사용자 정보 수정**
    - 사용자는 자신의 사용자 이름과 이메일을 수정할 수 있으며, 수정된 정보가 반환됨.


- **사용자 삭제**
    - 사용자는 자신의 계정을 삭제할 수 있으며, 해당 ID로 사용자를 찾아 삭제를 수행함.


- **게시물 생성**
    - 사용자는 새로운 게시물을 생성할 수 있으며, 제목과 내용을 입력하여 저장함.


- **게시물 조회**
    - 사용자는 ID로 특정 게시물을 조회할 수 있으며, ResponseEntity<ResponseDto> 형식으로 항목 정보를 제공함.


- **게시물 수정**
    - 사용자는 기존의 게시물을 수정할 수 있으며, 새로운 제목과 내용을 입력하여 업데이트함.


- **게시물 삭제**
    - 사용자는 ID로 특정 게시물을 삭제할 수 있으며, 해당 ID로 게시물을 찾아 삭제를 수행함.

# 3. Technology Stack (기술 스택)

## Language

|        |                                                                                                                  |
|--------|------------------------------------------------------------------------------------------------------------------|
| Java   | <img src="https://github.com/jihwanprogramer/calulators/blob/main/img/Java.jpg?raw=true" alt="Java" width="200"> | 
| Spring | Spring Framework를 사용하여 RESTful API 구현                                                                            |
| JPA    | 데이터베이스와의 연결 및 CRUD 작업을 위한 JPA 사용                                                                                 |

## Version Control

|     |                                                                                                   |
|-----|---------------------------------------------------------------------------------------------------|
| Git | <img src="https://github.com/jihwanprogramer/Kiosk/blob/main/image/GIT.jpg?raw=true" width="200"> |

## JDK Version

|        |                                                                                                                  |
|--------|------------------------------------------------------------------------------------------------------------------|
| JDK 17 | <img src="https://github.com/jihwanprogramer/Kiosk/blob/main/image/JDK23.jpg?raw=true" alt="JDK 11" width="180"> |

|

<br/>

# 4. Project Structure (프로젝트 구조)

```
└─newsfeed
    │  NewSfeedApplication.java                # 애플리케이션 시작점
    │
    ├─board
    │  ├─controller
    │  │      BoardController.java              # 게시판 관련 요청 처리
    │  │
    │  ├─dto
    │  │      BoardResponseDto.java              # 게시판 응답 DTO
    │  │      CreateRequestDto.java              # 게시판 생성 요청 DTO
    │  │      CreateResponseDto.java             # 게시판 생성 응답 DTO
    │  │      LikesResponseDto.java              # 게시판 좋아요 응답 DTO
    │  │      PageResponseDto.java               # 페이지네이션 응답 DTO
    │  │      PeriodRequestDto.java              # 기간 요청 DTO
    │  │      UpdateRequestDto.java              # 게시판 수정 요청 DTO
    │  │
    │  ├─entity
    │  │      Board.java                          # 게시판 엔티티
    │  │
    │  ├─repository
    │  │      BoardRepository.java                # 게시판 데이터 접근
    │  │
    │  └─service
    │          BoardService.java                  # 게시판 서비스 인터페이스
    │          BoardServiceImpl.java              # 게시판 서비스 구현
    │
    ├─comment
    │  ├─controller
    │  │      CommentController.java              # 댓글 관련 요청 처리
    │  │
    │  ├─dto
    │  │      CommentPageResponseDto.java         # 댓글 페이지 응답 DTO
    │  │      CommentResponseDto.java             # 댓글 응답 DTO
    │  │      CommentSaveRequestDto.java          # 댓글 저장 요청 DTO
    │  │      CommentUpdateRequestDto.java        # 댓글 수정 요청 DTO
    │  │
    │  ├─entity
    │  │      Comment.java                        # 댓글 엔티티
    │  │
    │  ├─repository
    │  │      CommentRepository.java              # 댓글 데이터 접근
    │  │
    │  └─service
    │          CommentService.java                # 댓글 서비스 인터페이스
    │          CommentServiceImp.java             # 댓글 서비스 구현
    │
    ├─common
    │  │  Const.java                             # 상수 정의
    │  │
    │  ├─config
    │  │      PasswordEncoder.java                # 비밀번호 인코더
    │  │      WebConfig.java                      # 웹 관련 설정
    │  │
    │  ├─entity
    │  │      BaseEntity.java                     # 기본 엔티티 (생성일, 수정일 포함)
    │  │
    │  ├─filter
    │  │      LoginFilter.java                    # 로그인 필터
    │  │
    │  └─handler
    │          ErrorResponseDto.java              # 오류 응답 DTO
    │          GlobalExceptionHandler.java         # 전역 예외 처리기
    │          GlobalFieldError.java              # 필드 오류 처리
    │
    ├─exception
    │      AccessDeniedException.java              # 접근 거부 예외
    │      AlreadyExistsException.java             # 이미 존재하는 예외
    │      LoginAuthException.java                 # 로그인 인증 예외
    │      MisMatchPasswordException.java          # 비밀번호 불일치 예외
    │      MisMatchUserException.java              # 사용자 불일치 예외
    │      NullResponseException.java              # 널 응답 예외
    │      SelfFollowNotAllowedException.java      # 자기 자신을 팔로우할 수 없는 예외
    │      WrongPasswordException.java             # 잘못된 비밀번호 예외
    │
    ├─follow
    │  ├─controller
    │  │      FollowController.java                # 팔로우 관련 요청 처리
    │  │
    │  ├─dto
    │  │      FollowResponseDto.java               # 팔로우 응답 DTO
    │  │      FollowSingleResponseDto.java         # 개별 팔로우 응답 DTO
    │  │
    │  ├─entity
    │  │      Follow.java                          # 팔로우 엔티티
    │  │
    │  ├─repository
    │  │      FollowRepository.java                # 팔로우 데이터 접근
    │  │
    │  └─service
    │          FollowService.java                  # 팔로우 서비스 인터페이스
    │          FollowServiceImpl.java              # 팔로우 서비스 구현
    │
    ├─like
    │  ├─controller
    │  │      BoardLikeController.java            # 게시판 좋아요 관련 요청 처리
    │  │      CommentLikeController.java           # 댓글 좋아요 관련 요청 처리
    │  │
    │  ├─dto
    │  │      BoardLikeResponseDto.java           # 게시판 좋아요 응답 DTO
    │  │      CommentLikeResponseDto.java          # 댓글 좋아요 응답 DTO
    │  │
    │  ├─entity
    │  │      BoardLike.java                       # 게시판 좋아요 엔티티
    │  │      CommentLike.java                     # 댓글 좋아요 엔티티
    │  │
    │  ├─repository
    │  │      BoardLikeRepository.java             # 게시판 좋아요 데이터 접근
    │  │      CommentLikeRepository.java            # 댓글 좋아요 데이터 접근
    │  │
    │  └─service
    │          BoardLikeService.java               # 게시판 좋아요 서비스 인터페이스
    │          BoardLikeServiceImpl.java           # 게시판 좋아요 서비스 구현
    │          CommentLikeService.java              # 댓글 좋아요 서비스 인터페이스
    │          CommentLikeServiceImpl.java         # 댓글 좋아요 서비스 구현
    │
    ├─login
    │  ├─controller
    │  │      LoginController.java                 # 로그인 관련 요청 처리
    │  │
    │  ├─dto
    │  │      LoginRequestDto.java                 # 로그인 요청 DTO
    │  │      LoginResponseDto.java                # 로그인 응답 DTO
    │  │
    │  ├─repository
    │  │      LoginRepository.java                 # 로그인 데이터 접근
    │  │
    │  └─service
    │          LoginService.java                   # 로그인 서비스 인터페이스
    │
    └─user
        ├─controller
        │      UserController.java                 # 사용자 관련 요청 처리
        │
        ├─dto
        │      UpdateUserRequestDto.java           # 사용자 수정 요청 DTO
        │      UserRequestDto.java                  # 사용자 요청 DTO
        │      UserResponseDto.java                 # 사용자 응답 DTO
        │
        ├─entity
        │      User.java                           # 사용자 엔티티
        │
        ├─repository
        │      UserRepository.java                  # 사용자 데이터 접근
        │
        └─service
                UserService.java                   # 사용자 서비스 인터페이스
                UserServiceImpl.java                # 사용자 서비스 구현

```

<br/>

# 5. Development Workflow (개발 워크플로우)

## 브랜치 전략 (Branch Strategy)

적절한 클래스 사용과 다양한 메소드 활용이 주목적이기에 빠르게 수정 가능한
직접적인 Main Branch을 바로 사용했습니다.

## 블록 구문

한 줄짜리 블록일 경우라도 {}를 생략하지 않고, 명확히 줄 바꿈 하여 사용한다

```
if (session != null) {
     session.invalidate();
}
```

<br/>
<br/>   
카멜 표기법을 이용하여 가독성을 향상시켰다.

```
private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;

```

<br/>

## 메소드 네이밍

메소드 작성 시 아래 네이밍 규칙을 준수하여 의미 전달을 명확하게 한다.<br/>
메소드명이 길어지더라도 의미 전달의 명확성에 목적을 두어 작성한다.<br/>

```
public class PasswordEqualsCheckException extends RuntimeException
```

# 6. 트러블 슈팅

https://computerreport.tistory.com/86

# 7. 수행 결과

## 1.board 생성

```
POST /boards
Body: {
    "title":"제목",
    "contents":"내용"
}
```

<br/>

## 2.board 전체 항목 조회

```
GET /boards
```

<br/>

## 3.board 선택 조회

```
GET /boards/{id}
```

<br/>

<br/>

## 4.board 페이지 조회

```
GET /boards/pages?page={page}
```

<br/>

## 5.board 페이지 

```
PATCH /todos/{id}
Body: {
    "title":"수정",
    "contents":"수정 내용"
}
```

<br/>

## 5.Todo 항목 삭제

```
DELETE /todos/{id}
```

<br/>

## 6.유저 생성

```
POST /users/signup
Body: {
    "userName":"유저명",
    "email":"kob882333@naver.com",
    "password":"12345678"
}
```

<br/>

## 7.전체 유저 조회

```
GET /users
```

<br/>

## 8.유저 상세 조회

```
GET /users/{id}
```

<br/>

## 9.유저 수정

```
PATCH /users/{id}
Body:{
    "title":"제목 수정"
    "email":"이메일 수정"
}
```

<br/>

## 10.유저 삭제

```
DELETE /users/{id}
```

<br/>

## 11.로그인

```
POST /session-login
Body:{
    "email" : "kob882333@naver.com",
    "password" : "12345678"
}
```

<br/>

## 12.로그아웃

```
POST /session-logout
```

<br/>

## 13.이메일 중복확인

```
GET /check?email={email}
```

