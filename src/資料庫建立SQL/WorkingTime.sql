--------清除----------
 
DROP TABLE WORKTIME; 
DROP TABLE WORKER;
DROP TABLE JOB;

DROP SEQUENCE worktime_seq;
DROP SEQUENCE worker_seq;
DROP SEQUENCE job_seq;



----------建立JOB表格----------

CREATE TABLE JOB(
JOBNO           VARCHAR2(5) NOT NULL,
JOBTYPE         VARCHAR2(200) NOT NULL,


CONSTRAINT JOB_PRIMARY_KEY PRIMARY KEY (JOBNO)
);

----------建立WORKER表格----------

CREATE TABLE WORKER(
WORKERNO           VARCHAR2(5) NOT NULL,
WORKERNAME         VARCHAR2(30) NOT NULL,
WORKERID           VARCHAR2(20) NOT NULL,
WORKERPW           VARCHAR2(20) NOT NULL,

CONSTRAINT WORKER_PRIMARY_KEY PRIMARY KEY (WORKERNO)
);



----------建立WORKTIME表格----------

CREATE TABLE WORKTIME(
WORKTIMENO          VARCHAR2(5)    NOT NULL,
WORKERNO              VARCHAR2(5)     NOT NULL,
JOBNO             VARCHAR2(5)     NOT NULL,
JOBINTRO        VARCHAR2(200) NOT NULL,
WORKTIMESTART        TIMESTAMP       NOT NULL,
WORKTIMEEND        TIMESTAMP       NOT NULL,

CONSTRAINT WORKTIME_PRIMARY_KEY PRIMARY KEY (WORKTIMENO),
CONSTRAINT WORKTIME_FOREIGN_KEY FOREIGN KEY (WORKERNO) REFERENCES WORKER(WORKERNO),
CONSTRAINT WORKTIME_FOREIGN_KEY2 FOREIGN KEY (JOBNO) REFERENCES JOB(JOBNO)
);



 ------------------創建SEQ-------------------


CREATE SEQUENCE job_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

CREATE SEQUENCE worker_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;



CREATE SEQUENCE worktime_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


--使用者假資料--
INSERT INTO WORKER VALUES ('W'||LPAD(to_char(WORKER_SEQ.NEXTVAL), 4, '0'),'模適','Moze@moze.com','123456');
INSERT INTO WORKER VALUES ('W'||LPAD(to_char(WORKER_SEQ.NEXTVAL), 4, '0'),'爪哇','Java@moze.com','123456');

--工作類別假資料--
INSERT INTO JOB VALUES ('J'||LPAD(to_char(JOB_SEQ.NEXTVAL), 4, '0'),'管理財經');
INSERT INTO JOB VALUES ('J'||LPAD(to_char(JOB_SEQ.NEXTVAL), 4, '0'),'行銷業務');
INSERT INTO JOB VALUES ('J'||LPAD(to_char(JOB_SEQ.NEXTVAL), 4, '0'),'軟體工程');

--工時假資料--
INSERT INTO WORKTIME VALUES ('T'||LPAD(to_char(WORKTIME_SEQ.NEXTVAL), 4, '0'),'W0001','J0002','門市管理與業務銷售',TO_DATE('2020-08-27 17:00:00','yyyy-mm-dd hh24:mi:ss'),TO_DATE('2020-08-27 21:00:00','yyyy-mm-dd hh24:mi:ss'));
INSERT INTO WORKTIME VALUES ('T'||LPAD(to_char(WORKTIME_SEQ.NEXTVAL), 4, '0'),'W0001','J0002','門市管理與業務銷售',TO_DATE('2020-08-20 17:00:00','yyyy-mm-dd hh24:mi:ss'),TO_DATE('2020-08-20 21:00:00','yyyy-mm-dd hh24:mi:ss'));
INSERT INTO WORKTIME VALUES ('T'||LPAD(to_char(WORKTIME_SEQ.NEXTVAL), 4, '0'),'W0001','J0002','門市管理與業務銷售',TO_DATE('2020-08-10 17:00:00','yyyy-mm-dd hh24:mi:ss'),TO_DATE('2020-08-10 21:00:00','yyyy-mm-dd hh24:mi:ss'));
INSERT INTO WORKTIME VALUES ('T'||LPAD(to_char(WORKTIME_SEQ.NEXTVAL), 4, '0'),'W0001','J0001','記帳出納與財務分析',TO_DATE('2020-08-15 17:00:00','yyyy-mm-dd hh24:mi:ss'),TO_DATE('2020-08-15 21:00:00','yyyy-mm-dd hh24:mi:ss'));
INSERT INTO WORKTIME VALUES ('T'||LPAD(to_char(WORKTIME_SEQ.NEXTVAL), 4, '0'),'W0002','J0003','網站架設與系統設計',TO_DATE('2020-08-21 17:00:00','yyyy-mm-dd hh24:mi:ss'),TO_DATE('2020-08-21 21:00:00','yyyy-mm-dd hh24:mi:ss'));
INSERT INTO WORKTIME VALUES ('T'||LPAD(to_char(WORKTIME_SEQ.NEXTVAL), 4, '0'),'W0002','J0003','網站架設與系統設計',TO_DATE('2020-08-11 17:00:00','yyyy-mm-dd hh24:mi:ss'),TO_DATE('2020-08-11 21:00:00','yyyy-mm-dd hh24:mi:ss'));