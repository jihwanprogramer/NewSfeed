# 1. Project Overview (프로젝트 개요)

- 프로젝트 이름: FACKBOOK
- 프로젝트 설명: Spring 프레임워크 와 JPA 를 이용하고 Session과 Filter를 이용하여 SNS NewsFeed만들어 보기

- 팀 9조
  
| 역할 | 팀장 | 팀원 | 팀원 | 팀원 | 팀원 |
|:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|:-------------:|
|프로필|![image](https://avatars.githubusercontent.com/u/196007904?v=4&size=64)|![image](https://avatars.githubusercontent.com/u/130281249?v=4&size=64)|![image](https://avatars.githubusercontent.com/u/194772779?v=4&size=64)|![image](https://avatars.githubusercontent.com/u/198323298?v=4&size=64)|![image](https://avatars.githubusercontent.com/u/191333665?s=64&v=4)|
|이름|김규현|김지환|김채원|류경선|오병택|
|GitHub|0122-0|jihwanprogramer|chaewon9999|RKS-t|byeongtaek12|
|기술블로그|[규현velog](https://velog.io/@flowercat95/posts)|[지환tistory](https://computerreport.tistory.com/)|[채원velog](https://velog.io/@w0729/posts)|[경선tistory](https://rudtjs2.tistory.com/)|[병택velog](https://velog.io/@byeongtaek12/posts)|
  
## API 명세서

![1](https://github.com/user-attachments/assets/d149cf3b-e5ea-410f-a9bf-cc30927a16ab)
![2](https://github.com/user-attachments/assets/8001f5b4-c15a-42a2-9ebb-c5d49a71a877)
![3](https://github.com/user-attachments/assets/2a99704b-77c3-4202-a658-c7800452f188)
![4](https://github.com/user-attachments/assets/2146f96f-0c75-4b41-94ad-df3359dd56a3)
![5](https://github.com/user-attachments/assets/ac30809c-c4f5-44b7-870e-41959c427bdf)
![6](https://github.com/user-attachments/assets/ca907a82-ad69-49bb-98cb-41ef30bb7a0e)
![7](https://github.com/user-attachments/assets/187ddbac-c7ea-4e61-81e1-7b76ede905d6)
![8](https://github.com/user-attachments/assets/4062076e-bf0a-4ff2-9cef-069063c52ff7)
![9](https://github.com/user-attachments/assets/4701288c-4571-4c34-9a8b-6bd1a30cbd02)
![10](https://github.com/user-attachments/assets/b5a05f92-2297-4123-9242-2726ed6245cc)
![11](https://github.com/user-attachments/assets/1ae8f122-4edc-438b-8b38-6dd1be708162)
![12](https://github.com/user-attachments/assets/c1f12c58-13a6-4a05-acd4-4bee8c292dce)
![13](https://github.com/user-attachments/assets/98fd16ce-1cef-4b05-8540-19467699fe66)
![14](https://github.com/user-attachments/assets/f4e2ad23-b1c0-4e21-a870-ce7dd6a7ce2b)
![15](https://github.com/user-attachments/assets/a2fb5fba-09f9-44be-bcfe-35bb30d93430)
![16](https://github.com/user-attachments/assets/c607b83d-950d-4d89-b2e7-290531c3f3a0)

## ERD

![ERD 1](https://github.com/user-attachments/assets/fc0b7575-c263-4bd3-b8f4-5e7b73d36db5)



# 2. Key Features (주요 기능)

<h3>회원</h3>

- **사용자 등록**
    - 사용자는 새로운 계정을 생성할 수 있으며, 이메일, 비밀번호, 이름, 나이를 입력하여 등록함.


- **사용자 로그인**
    - 사용자는 등록된 이메일과 비밀번호로 로그인 가능

- **사용자 로그아웃**
    - 사용자가 토글로 로그아웃 가능.     

- **사용자 정보 조회**
    - 사용자는 자신의 ID또는 이름으로 검색 가능


- **사용자 정보 수정**
    - 사용자는 자신의 사용자 이름, 이메일, 나이, 비밀번호를 수정할 수 있으며, 수정된 정보중 이름과 나이가 반환됨.


- **사용자 삭제**
    - 사용자는 자신의 계정을 삭제할 수 있으며, 해당 ID와 비밀번호를 입력하여 삭제 가능, 삭제를 할 경우 본인의 게시물, 게시물 좋아요 내역, 댓글, 댓글 좋아요 내역, 팔로우 관계가 모두 삭제 됩니다.

<h3>게시물</h3>

- **게시물 생성**
    - 로그인 후 사용자는 새로운 게시물을 생성할 수 있으며, 제목과 내용을 입력하여 저장함.


- **게시물 조회**
  - 사용자는 모든 게시물을 조회 하거나 특정 게시물을 조회할 수 있습니다. ResponseEntity<ResponseDto> 형식으로 항목 정보를 제공함.


- **게시물 수정**
    - 로그인 후 사용자는 기존의 게시물을 수정할 수 있으며, 새로운 제목과 내용을 입력하여 업데이트함.


- **게시물 삭제**
    - 로그인 후 사용자는 본인의 게시글을 삭제 할 수 있습니다.

<h3>댓글</h3>

- **댓글 생성**
    - 로그인 후 사용자는 게시물에 새로운 댓글을 생성할 수 있으며, 내용을 입력하여 저장함.


- **댓글 조회**
  - 사용자는 원하는 게시물 모든 댓글을 조회할 수 있습니다. ResponseEntity<ResponseDto> 형식으로 항목 정보를 제공함.
  - 또는 댓글 페이징 기능을 사용하여 댓글만 조회 할수 있습니다.

- **댓글 수정**
    - 로그인 후 사용자는 기존의 댓글을 수정할 수 있으며, 새로운 내용을 입력하여 업데이트함.


- **댓글 삭제**
    - 로그인 후 사용자는 본인의 댓글을 삭제 할 수 있습니다.

<h3>팔로우</h3>

- **팔로우 생성**
    - 로그인 후 사용자는 토글을 통해 팔로우를 생성 합니다.


- **팔로우 수정**
  - 생성된 팔로우를 토글을 하여 true,false로 변환 가능.
  - true = 활성화 , flase = 비활성화


- **팔로우 조회**
    - 로그인 후 사용자는 자신이 팔로우 하고 있는 회원의 팔로워, 팔로잉 조회 가능

<h3>게시물 좋아요</h3>

- **게시물 좋아요 생성**
    - 로그인 후 사용자는 토글을 통해 게시물 좋아요를 생성 합니다.


- **게시물 좋아요 수정**
  - 생성된 좋아요를 토글을 하여 true,false로 변환 가능.
  - true = 활성화 , flase = 비활성화

- **게시물 좋아요 조회**
  - 생성된 좋아요를 조회하여 해당 게시물에 좋아요 갯수 확인

<h3>댓글 좋아요</h3>

- **댓글 좋아요 생성**
    - 로그인 후 사용자는 토글을 통해 댓글 좋아요를 생성 합니다.


- **댓글 좋아요 수정**
  - 생성된 좋아요를 토글을 하여 true,false로 변환 가능.
  - true = 활성화 , flase = 비활성화

- **댓글 좋아요 조회**
  - 생성된 좋아요를 조회하여 해당 댓글에 좋아요 갯수 확인 

# 3. Technology Stack (기술 스택)

## Language

<img src="https://img.shields.io/badge/java-007396?style=flat-square&logo=java&logoColor=white"/><img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>

## Version Control

<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white"/><img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white"/><img src="https://img.shields.io/badge/Postman-FF6C37?style=flat-square&logo=Postman&logoColor=white"/>

## JDK Version

Java 17 (OpenJDK 17)


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

- 공동 작업을 위해 `dev` 브랜치를 생성하여 기능 개발의 기준 브랜치(origin) 로 사용함
- 개인 작업은 `feature/기능명` 형식의 브랜치를 dev 브랜치에서 분리하여 개발
- 개발 완료시 pull request로 `dev` 브랜치에 병합
- 

## 블록 구문

한 줄짜리 블록일 경우라도 {}를 생략하지 않고, 명확히 줄 바꿈 하여 사용

```
if (session != null) {
     session.invalidate();
}
```

<br/>
<br/>   
카멜 표기법을 이용하여 가독성을 향상

```
private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;

```

<br/>

## 메소드 네이밍 통일

메소드 작성 시 아래 네이밍 규칙을 준수하여 의미 전달을 명확하게 함<br/>

```
- 생성 create{필드명}  
- 조회 find{필드명}by{조회기준} 
- 페이지 적용시 findPage{필드명}By{조회기준} 
- 수정 update{필드명} 
- 삭제 delete{필드명}
```

## 예외처리

예외 발생시 커스텀 예외를 설정해 처리
```
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}
```

## 생성자 & Setter
데이터베이스 보호를 위해 생성자 사용 지양 <br/>
setter를 엔티티 내부에서 사용하므로써 보안 향상
```
public static Board of(CreateRequestDto requestDto, User user) {
        Board board = new Board(requestDto);
        board.initUser(user);
        return board;
    }
```

## 연관관계
@ManyToOne, @OneToMany를 활용해 연관관계 구현
```
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
    
@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
private List<Comment> comments = new ArrayList<>();
```

# 6. 트러블 슈팅


# 7. 수행 결과


## 1.게시물 생성

```
POST /boards
Body: {
    "title":"제목",
    "contents":"내용"
}
```

<br/>

## 2.게시물 전체 항목 조회

```
GET /boards
```

<br/>

## 3.게시물 선택 조회

```
GET /boards/{id}
```

<br/>

<br/>

## 4.게시물 페이지 조회

```
GET /boards/pages?page={page}
```

<br/>

## 5.게시물 수정일자순 조회

```
GET /boards/sorted-by-modifiedAt?page={page}
```

<br/>

## 6.게시물 기간별 조회

```
GET /boards/period?startDate={startDate}&endDate={endDate}
```

<br/>

## 7.게시물 좋아요 순 조회

```
GET /boards/like
```

<br/>

## 8. 게시물 수정

```
PATCH /boards/{id}
Body: {
    "title":"제목",
    "contents":"내용"
}
```

<br/>

## 9. 게시물 삭제

```
DELETE /boards/{id}
```

<br/>

## 10.유저 생성

```
POST /users/signup
Body: {
    "name": "사용자 이름",
    "age": 나이,
    "email": "이메일",
    "password": "비밀번호",
    "checkPassword": "비밀번호 확인"
}
```

<br/>

## 11.이메일로 유저 조회

```
GET /users?email={email}
```

<br/>

## 12. 아이디로 유저 조회

```
GET /users/{id}
```

<br/>

## 13. 유저 수정

```
PATCH /users/{id}
Body: {
    "name": "사용자 이름",
    "age": 나이,
    "email": "이메일",
    "password": "비밀번호",
    "checkPassword": "비밀번호 확인"
}
```

<br/>

## 14. 유저 삭제

```
DELETE /users/{id}
```

## 15. 댓글 생성

```
POST /boards/{id}/comments
Body: {
    "content":"댓글 내용"
}
```
## 16. 댓글 조회

```
GET /boards/{id}/comments
```

## 17. 댓글 수정
```
PATCH /boards/comments/{id}
Body: {
    "content":"댓글 내용"
}
```
## 18. 댓글 삭제
```
DELETE /boards/comments/{id}
```
## 19. 댓글 페이지네이션
```
GET /boards/comments/pages
```
## 20. 팔로우
```
POST /users/{id}/follow
```
## 21. 팔로우 취소
```
PATCH /{id}/follow
```
## 22. 팔로잉 목록 조회
```
GET /users/{id}/followings
```
## 23. 팔로워 목록 조회
```
GET /users/{id}/followers
```
## 24. 게시물 좋아요
```
POST /boards/{id}/like
```
## 25. 게시물 좋아요 취소
```
PATCH /boards/{id}/like
```
## 26. 게시물 좋아요 수 조회
```
GET /boards/{id}/like
```
## 27. 댓글 좋아요
```
POST /comments/{id}/like
```
## 28. 댓글 좋아요 취소
```
PATCH /comments/{id}/like
```
## 29. 댓글 좋아요 수 조회
```
GET /{id}/like
```
## 30. 로그인
```
POST /login
BODY: {
    "email":"이메일",
    "password":"비밀번호"
}
```
## 31. 로그아웃
```
POST /logout
```
