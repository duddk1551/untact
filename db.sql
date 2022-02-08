#DB생성
DROP DATABASE IF EXISTS insta;
CREATE DATABASE insta;
USE insta;

#게시물 테이블 생성
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '번호',
    regDate DATETIME NOT NULL COMMENT '작성날짜',
    updateDate DATETIME NOT NULL COMMENT '수정날짜',
    boardId INT(10) UNSIGNED NOT NULL COMMENT '게시판번호',
    memberId INT(10) UNSIGNED NOT NULL COMMENT '회원번호',
    title CHAR(100) NOT NULL COMMENT '제목',
    content TEXT NOT NULL COMMENT '본문',
    blindStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '블라인드여부',
    blindDate DATETIME COMMENT '블라인드날짜',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '삭제여부',
    delDate DATETIME COMMENT '삭제날짜',
    hitCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '조회수',
    repliesCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '댓글수',
    likeCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '좋아요수',
    dislikeCount INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '싫어요수'
)ENGINE=INNODB DEFAULT CHARSET=utf8;

#게시물 테스트 데이터 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
memberId = 1,
title = '제목1',
content = '본문1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
memberId = 1,
title = '제목2',
content = '본문2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
memberId = 1,
title = '제목3',
content = '본문3';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
memberId = 2,
title = '제목4',
content = '본문4';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
boardId = 2,
memberId = 2,
title = '제목5',
content = '본문5;