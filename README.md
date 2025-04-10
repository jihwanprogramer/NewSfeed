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

## Board API
| **Method** | **Endpoint**          | **Description**            | **Parameters**                                                                              | **Request Body**                                     | **Response**                                                                                         | **Status Code**  |
|------------|-----------------------|----------------------------|---------------------------------------------------------------------------------------------|------------------------------------------------------|------------------------------------------------------------------------------------------------------|------------------|
| `POST`     | `/boards`             | 게시글 생성                | **Session:** <br> - `loginUser` (UserResponseDto)                                         | `{ "title": string, "content": string }`             | `{ "id": long, "userId": long, "title": string, "content": string, "createdAt": string, "updatedAt": string }` | `201 Created`    |
| `GET`      | `/boards`             | 게시글 전체 조회           | 없음                                                                                       | 없음                                                 | `[ { "id": long, "userId": long, "title": string, "content": string, "createdAt": string, "updatedAt": string }, ... ]` | `200 OK`         |
| `GET`      | `/boards/pages`       | 게시글 페이지 조회         | **Query:** <br> - `page` (int, default: 1)                                               | 없음                                                 | `Page<PageResponseDto>`                                                                               | `200 OK`         |
| `GET`      | `/boards/{id}`        | 특정 게시글 조회           | **Path:** <br> - `id` (Long)                                                              | 없음                                                 | `{ "id": long, "userId": long, "title": string, "content": string, "createdAt": string, "updatedAt": string }` | `200 OK`         |
| `PATCH`    | `/boards/{id}`        | 게시글 수정                | **Path:** <br> - `id` (Long) <br> **Session:** <br> - `loginUser` (UserResponseDto)      | `{ "title": string, "content": string }`             | `{ "id": long, "userId": long, "title": string, "content": string, "createdAt": string, "updatedAt": string }` | `200 OK`         |
| `DELETE`   | `/boards/{id}`        | 게시글 삭제                | **Path:** <br> - `id` (Long) <br> **Session:** <br> - `loginUser` (UserResponseDto)      | 없음                                                 | 없음                                                                                                | `204 No Content`  |
| `GET`      | `/boards/sorted-by-modifiedAt` | 수정일자 기준 최신순으로 조회 | **Query:** <br> - `page` (int, default: 1)                                               | 없음                                                 | `Page<PageResponseDto>`                                                                               | `200 OK`         |
| `GET`      | `/boards/period`      | 기간별 조회                | **Query:** <br> - `page` (int, default: 1)                                               | `{ "startDate": string, "endDate": string }`        | `Page<PageResponseDto>`                                                                               | `200 OK`         |
| `GET`      | `/boards/like`        | 좋아요순으로 조회          | **Query:** <br> - `page` (int, default: 1)                                               | 없음                                                 | `Page<LikesResponseDto>`                                                                              | `200 OK`         |
## Comment API
| **Method** | **Endpoint**                           | **Description**                            | **Parameters**                                                                                         | **Request Body**             | **Response**                                                                                                      | **Status Code** |
|------------|----------------------------------------|--------------------------------------------|--------------------------------------------------------------------------------------------------------|------------------------------|-------------------------------------------------------------------------------------------------------------------|-----------------|
| `POST`     | `/boards/{board_id}/comments`         | 댓글 생성                                   | **Path:** <br> - `board_id`: 게시판 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long) | `{ "content": string }`      | `{ "id": long, "userId": long, "scheduleId": long, "content": string, "createdAt": string, "updatedAt": string }`    | `201 Created`   |
| `GET`      | `/boards/{board_id}/comments`         | 특정 게시판에 속한 댓글 목록 조회              | **Path:** <br> - `board_id`: 게시판 ID (Long)                                                       | 없음                         | `[ { "id": long, "userId": long, "scheduleId": long, "content": string, "createdAt": string, "updatedAt": string }, ... ]` | `200 OK`        |
| `GET`      | `/comments/{id}`                       | 단일 댓글 조회                             | **Path:** <br> - `id`: 댓글 ID (Long)                                                                | 없음                         | `{ "id": long, "userId": long, "scheduleId": long, "content": string, "createdAt": string, "updatedAt": string }`     | `200 OK`        |
| `PUT`      | `/comments/{id}`                       | 댓글 수정                                   | **Path:** <br> - `id`: 댓글 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long)         | `{ "content": string }`      | `{ "id": long, "userId": long, "scheduleId": long, "content": string, "createdAt": string, "updatedAt": string }`     | `200 OK`        |
| `DELETE`   | `/comments/{id}`                       | 댓글 삭제                                   | **Path:** <br> - `id`: 댓글 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long)         | 없음                         | 없음                                                                                                              | `204 No Content` |
## Follow API
| **Method** | **Endpoint**                      | **Description**                                     | **Parameters**                                                                                               | **Request Body** | **Response**                                     | **Status Code** |
|------------|-----------------------------------|-----------------------------------------------------|--------------------------------------------------------------------------------------------------------------|-------------------|--------------------------------------------------|-----------------|
| `POST`     | `/users/{followId}/follow`       | 다른 사용자를 팔로우                               | **Path:** <br> - `followId`: 팔로우 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long)| 없음              | `{ "followId": long, "userId": long, "followed": boolean }` | `201 Created`   |
| `PATCH`    | `/users/{followingId}/follow`    | 팔로우 상태를 토글                                 | **Path:** <br> - `followingId`: 팔로우 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long)| 없음              | `{ "followingId": long, "userId": long, "followed": boolean }` | `200 OK`        |
| `GET`      | `/users/{userId}/follow-status`  | 팔로우 중인지 확인                                 | **Path:** <br> - `userId`: 확인할 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long)| 없음              | `{ "userId": long, "following": boolean }`     | `200 OK`        |
| `GET`      | `/users/{userId}/followings`     | 특정 사용자가 팔로우한 사용자 목록 조회          | **Path:** <br> - `userId`: 조회 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long)| 없음              | `Page<FollowResponseDto>`                         | `200 OK`        |
| `GET`      | `/users/{userId}/followers`      | 특정 사용자를 팔로우한 사용자 목록 조회          | **Path:** <br> - `userId`: 조회 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long)| 없음              | `Page<FollowResponseDto>`                         | `200 OK`        |
## Like API
### Board Like API
| **Method** | **Endpoint**                    | **Description**                       | **Parameters**                | **Request Body**   | **Response**                                     | **Status Code** |
|------------|---------------------------------|---------------------------------------|-------------------------------|---------------------|--------------------------------------------------|-----------------|
| `POST`     | `/boards/{boardid}/like`       | 게시물 좋아요를 생성합니다.           | **Path:**<br> - `boardid`: 게시물 ID (Long) <br> **Session:** <br> - `LOGIN_USER`: 사용자 ID (Long) | 없음                | `{ "boardId": long, "userId": long, "liked": boolean }` | `201 Created`   |
| `PATCH`    | `/boards/{boardid}/like`       | 게시물 좋아요를 토글합니다.           
## Follow API
| **Method** | **Endpoint**                      | **Description**                                     | **Parameters**                                                                                               | **Request Body** | **Response**                                     | **Status Code** |
|------------|-----------------------------------|-----------------------------------------------------|--------------------------------------------------------------------------------------------------------------|-------------------|--------------------------------------------------|-----------------|
| `POST`     | `/users/{followId}/follow`       | 다른 사용자를 팔로우                               | **Path:** <br> - `followId`: 팔로우 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long) | 없음              | `{ "followId": long, "userId": long, "followed": boolean }` | `201 Created`   |
| `PATCH`    | `/users/{followingId}/follow`    | 팔로우 상태를 토글                                 | **Path:** <br> - `followingId`: 팔로우 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long) | 없음              | `{ "followingId": long, "userId": long, "followed": boolean }` | `200 OK`        |
| `GET`      | `/users/{userId}/follow-status`  | 팔로우 중인지 확인                                 | **Path:** <br> - `userId`: 확인할 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long) | 없음              | `{ "userId": long, "following": boolean }`     | `200 OK`        |
| `GET`      | `/users/{userId}/followings`     | 특정 사용자가 팔로우한 사용자 목록 조회          | **Path:** <br> - `userId`: 조회 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long) | 없음              | `Page<FollowResponseDto>`                         | `200 OK`        |
| `GET`      | `/users/{userId}/followers`      | 특정 사용자를 팔로우한 사용자 목록 조회          | **Path:** <br> - `userId`: 조회 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long) | 없음              | `Page<FollowResponseDto>`                         | `200 OK`        |
## Like API
### Board Like API
| **Method** | **Endpoint**                    | **Description**                       | **Parameters**                | **Request Body**   | **Response**                                     | **Status Code** |
|------------|---------------------------------|---------------------------------------|-------------------------------|---------------------|--------------------------------------------------|-----------------|
| `POST`     | `/boards/{boardid}/like`       | 게시물 좋아요를 생성합니다.           | **Path:**<br> - `boardid`: 게시물 ID (Long) <br> **Session:** <br> - `LOGIN_USER`: 사용자 ID (Long) | 없음                | `{ "boardId": long, "userId": long, "liked": boolean }` | `201 Created`   |
| `PATCH`    | `/boards/{boardid}/like`       | 게시물 좋아요를 토글합니다.            | **Path:**<br> - `boardid`: 게시물 ID (Long) <br> **Session:** <br> - `LOGIN_USER`: 사용자 ID (Long) | 없음                | `{ "boardId": long, "userId": long, "liked": boolean }` | `200 OK`        |
| `GET`      | `/boards/{boardid}/like`       | 게시물 좋아요를 조회합니다.            | **Path:**<br> - `boardid`: 게시물 ID (Long) <br> **Session:** <br> - `LOGIN_USER`: 사용자 ID (Long) | 없음                | `{ "boardId": long, "userId": long, "liked": boolean }` | `200 OK`        |
### Comment Like API
| **Method** | **Endpoint**                    | **Description**                       | **Parameters**                | **Request Body**   | **Response**                                     | **Status Code** |
|------------|---------------------------------|---------------------------------------|-------------------------------|---------------------|--------------------------------------------------|-----------------|
| `POST`     | `/comments/{commentid}/like`    | 댓글 좋아요를 생성합니다.             | **Path:**<br> - `commentid`: 댓글 ID (Long) <br> **Session:** <br> - `LOGIN_USER`: 사용자 ID (Long) | 없음                | `{ "commentId": long, "userId": long, "liked": boolean }` | `201 Created`   |
| `PATCH`    | `/comments/{commentid}/like`    | 댓글 좋아요를 토글합니다.              | **Path:**<br> - `commentid`: 댓글 ID (Long) <br> **Session:** <br> - `LOGIN_USER`: 사용자 ID (Long) | 없음                | `{ "commentId": long, "userId": long, "liked": boolean }` | `200 OK`       아래는 `Follow API`부터 시작하는 API 문서입니다.
markdown
## Follow API
| **Method** | **Endpoint**                      | **Description**                                     | **Parameters**                                                                                               | **Request Body** | **Response**                                     | **Status Code** |
|------------|-----------------------------------|-----------------------------------------------------|--------------------------------------------------------------------------------------------------------------|-------------------|--------------------------------------------------|-----------------|
| `POST`     | `/users/{followId}/follow`       | 다른 사용자를 팔로우                               | **Path:** <br> - `followId`: 팔로우 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long) | 없음              | `{ "followId": long, "userId": long, "followed": boolean }` | `201 Created`   |
| `PATCH`    | `/users/{followingId}/follow`    | 팔로우 상태를 토글                                 | **Path:** <br> - `followingId`: 팔로우 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long) | 없음              | `{ "followingId": long, "userId": long, "followed": boolean }` | `200 OK`        |
| `GET`      | `/users/{userId}/follow-status`  | 팔로우 중인지 확인                                 | **Path:** <br> - `userId`: 확인할 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long) | 없음              | `{ "userId": long, "following": boolean }`     | `200 OK`        |
| `GET`      | `/users/{userId}/followings`     | 특정 사용자가 팔로우한 사용자 목록 조회          | **Path:** <br> - `userId`: 조회 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long) | 없음              | `Page<FollowResponseDto>`                         | `200 OK`        |
| `GET`      | `/users/{userId}/followers`      | 특정 사용자를 팔로우한 사용자 목록 조회          | **Path:** <br> - `userId`: 조회 대상 사용자 ID (Long) <br> **Session:** <br> - `loginUser`: 사용자 ID (Long) | 없음              | `Page<FollowResponseDto>`                         | `200 OK`        |
## Like API
### Board Like API
| **Method** | **Endpoint**                    | **Description**                       | **Parameters**                | **Request Body**   | **Response**                                     | **Status Code** |
|------------|---------------------------------|---------------------------------------|-------------------------------|---------------------|--------------------------------------------------|-----------------|
| `POST`     | `/boards/{boardid}/like`       | 게시물 좋아요를 생성합니다.           | **Path:**<br> - `boardid`: 게시물 ID (Long) <br> **Session:** <br> - `LOGIN_USER`: 사용자 ID (Long) | 없음                | `{ "boardId": long, "userId": long, "liked": boolean }` | `201 Created`   |
| `PATCH`    | `/boards/{boardid}/like`       | 게시물 좋아요를 토글합니다.            | **Path:**<br> - `boardid`: 게시물 ID (Long) <br> **Session:** <br> - `LOGIN_USER`: 사용자 ID (Long) | 없음                | `{ "boardId": long, "userId": long, "liked": boolean }` | `200 OK`        |
| `GET`      | `/boards/{boardid}/like`       | 게시물 좋아요를 조회합니다.            | **Path:**<br> - `boardid`: 게시물 ID (Long) <br> **Session:** <br> - `LOGIN_USER`: 사용자 ID (Long) | 없음                | `{ "boardId": long, "userId": long, "liked": boolean }` | `200 OK`        |
### Comment Like API
| **Method** | **Endpoint**                    | **Description**                       | **Parameters**                | **Request Body**   | **Response**                                     | **Status Code** |
|------------|---------------------------------|---------------------------------------|-------------------------------|---------------------|--------------------------------------------------|-----------------|
| `POST`     | `/comments/{commentid}/like`    | 댓글 좋아요를 생성합니다.             | **Path:**<br> - `commentid`: 댓글 ID (Long) <br> **Session:** <br> - `LOGIN_USER`: 사용자 ID (Long) | 없음                | `{ "commentId": long, "userId": long, "liked": boolean }` | `201 Created`   |
| `PATCH`    | `/comments/{commentid}/like`    | 댓글 좋아요를 토글합니다.              | **Path:**<br> - `commentid`: 댓글 ID (Long) <br> **Session:** <br> - `LOGIN_USER`: 사용자 ID (Long) | 없음                | `{ "commentId": long, "userId": long, "liked": boolean }` | `200 OK`        |
| `GET`      | `/comments/{commentid}/like`    | 댓글 좋아요를 조회합니다.              | **Path:**<br> - `commentid`: 댓글 ID (Long) <br> **Session:** <br> - `LOGIN_USER`: 사용자 ID (Long) | 없음                | `{ "commentId": long, "userId": long, "liked": boolean }` | `200 OK`        |
                                                                | `204 No Content` |
# API Documentation
## Login API
| **Method** | **Endpoint**    | **Description**          | **Parameters**                        | **Request Body**                                | **Response**                                   | **Status Code** |
|------------|------------------|--------------------------|---------------------------------------|-------------------------------------------------|------------------------------------------------|-----------------|
| `POST`     | `/login`         | 로그인 요청              | 없음                                  | `{ "email": string, "password": string }`      | `{ "message": "로그인에 성공하였습니다." }`   | `200 OK`        |
| `POST`     | `/logout`        | 로그아웃 요청            | 없음                                  | 없음                                            | `{ "message": "로그아웃하였습니다." }`        | `200 OK`        |
## User API
| **Method** | **Endpoint**         | **Description**                | **Parameters**                        | **Request Body**                                      | **Response**                                   | **Status Code**  |
|------------|----------------------|----------------------------------|---------------------------------------|-------------------------------------------------------|------------------------------------------------|------------------|
| `POST`     | `/users/signup`      | 회원 가입 요청                  | 없음                                  | `{ "name": string, "age": int, "email": string, "password": string, "checkPassword": string }` | `{ "id": long, "name": string, "email": string, ... }` | `201 Created`    |
| `GET`      | `/users`             | 사용자 목록 조회 (이름 검색)    | **Query:** <br> - `name` (string)   | 없음                                                  | `[ { "id": long, "name": string, "email": string }, ... ]` | `200 OK`        |
| `GET`      | `/users/pages`       | 사용자 목록 페이지 조회         | **Query:** <br> - `name` (string)   | 없음                                                  | `Page<UserResponseDto>`                        | `200 OK`        |
| `GET`      | `/users/{id}`        | 사용자 ID로 정보 조회           | **Path:** <br> - `id` (Long)        | 없음                                                  | `{ "id": long, "name": string, "email": string, ... }` | `200 OK`        |
| `PATCH`    | `/users/{id}`        | 사용자 정보 수정                | **Path:** <br> - `id` (Long)        | `{ "name": string, "age": int, "email": string, "password": string, "newPassword": string, "checkNewPassword": string }` | `{ "id": long, "name": string, "email": string, ... }` | `200 OK`        |
| `DELETE`   | `/users/{id}`        | 사용자 계정 삭제                | **Path:** <br> - `id` (Long)        | **Query:** <br> - `password` (string)                | 없음                                           | `204 No Content` |





[API 명세서 참고용](https://teamsparta.notion.site/9-FAKE-BOOK-1ce2dc3ef51480d5956de52b6fcea2de)
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

각각의 기능을 맡아서 서로 맡은 기능부분으로 브랜치를 나눈 뒤 코드리뷰를 하고 코드 컨벤션과 브랜치 컨벤션을 맞춤

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

- user를 삭제하려는 중에 삭제가 안되서 확인 해보니 user에서 boardrepository통해서 삭제를 하는데 board에서 연관관계설정이 안되어 있어서 삭제가 안됨 -> board에서는 method를 호출해서 지웠음 like and comment 를 user 에서 board로 삭제하려고 하면 method가 호출이 안되서 삭제 불가->
board에서 연관관계를 설정해서 해결

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
