USE newsfeed;
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '유저 식별자',
    name VARCHAR(50) NOT NULL COMMENT '유저명',
    age INTEGER COMMENT '나이',
    email VARCHAR(100) UNIQUE NOT NULL COMMENT '이메일',
    password VARCHAR(100) NOT NULL COMMENT '비밀번호',
    createdAt DATETIME COMMENT '생성일',
    modifiedAt DATETIME COMMENT '수정일'
);