/* Drop Tables */

DROP TABLE thread_reply CASCADE CONSTRAINT purge;
DROP TABLE thread_board CASCADE CONSTRAINT purge;
DROP TABLE thread_member CASCADE CONSTRAINT purge;
DROP TABLE thread_board_name CASCADE CONSTRAINT purge;

/* Drop Sequences */

DROP SEQUENCE seq_postnum;
DROP SEQUENCE seq_replynum;

/* Create Sequences */

CREATE SEQUENCE seq_postnum
START WITH 1
INCREMENT BY 1
NOMAXVALUE;

CREATE SEQUENCE seq_replynum
START WITH 1
INCREMENT BY 1
NOMAXVALUE;

/* Create Tables */

CREATE TABLE thread_board_name(
board_num number,
board_name varchar2(50),
primary key (board_num)
);

insert into thread_board_name values (10, 'LIFE');
insert into thread_board_name values (11, '음식');
insert into thread_board_name values (12, '뷰티');
insert into thread_board_name values (13, '패션');
insert into thread_board_name values (14, '연애');
insert into thread_board_name values (15, '운동');
insert into thread_board_name values (16, '꿀팁');

insert into thread_board_name values (20, 'HOBBY');
insert into thread_board_name values (21, '게임');
insert into thread_board_name values (22, '영화');
insert into thread_board_name values (23, '연예인');
insert into thread_board_name values (24, '여행');
insert into thread_board_name values (25, '만화');
insert into thread_board_name values (26, '문학');

insert into thread_board_name values (30, 'STUDY');
insert into thread_board_name values (31, '공부');
insert into thread_board_name values (32, '외국어');
insert into thread_board_name values (33, '취업');
insert into thread_board_name values (34, '알바');

insert into thread_board_name values (40, 'CHAT');
insert into thread_board_name values (41, '동아리');
insert into thread_board_name values (42, '고민');

CREATE TABLE thread_board
(
	thread_b_name varchar2(20) NOT NULL,
	thread_b_subject varchar2(100) NOT NULL,
	thread_b_content varchar2(2000) NOT NULL,
	thread_b_date date NOT NULL,
	thread_b_file varchar2(100), 
	thread_b_readcount number NOT NULL,
	thread_b_boardnum number NOT NULL,
	thread_b_postnum number,      /* 전체 게시판에서의 고유 게시글 번호 */
	PRIMARY KEY (thread_b_postnum)
);


CREATE TABLE thread_member
(
	thread_name varchar2(20) NOT NULL,
	thread_id varchar2(15),
	thread_pw varchar2(13) NOT NULL,
	thread_email varchar2(30) NOT NULL,
	PRIMARY KEY (thread_id)
);


CREATE TABLE thread_reply
(
	thread_r_name varchar2(20) NOT NULL,
	thread_r_content varchar2(1000) NOT NULL,
	thread_r_date date NOT NULL,
	thread_r_boardnum number NOT NULL,	/* 이게 각 게시물의 번호 (thread_board에서 thread_b_postnum) 이랑 일치 */
	thread_r_postnum number,   /* 이게 댓글 고유번호 */
	PRIMARY KEY (thread_r_postnum)
);

/* Insert Values */

INSERT into THREAD_MEMBER values ('관리자', 'admin', 'admin', 'admin');
INSERT into THREAD_MEMBER values ('사용자', 'asdf', 'asdf', 'asdf');

SELECT * FROM THREAD_MEMBER;
