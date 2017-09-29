TRUNCATE TABLE ROLE;

INSERT INTO ROLE (   ROLE_ID,   ROLE_NAME,   ADMIN_FLAG,   GUEST_FLAG,   EXPIRY_DATE,   REGIST_DATE,   REGIST_PNAME,   LAST_UPDATE_DATE,   LAST_UPDATE_PNAME ) VALUES (   'ADMIN',   'Administrator',   '1',   '0',   TO_TIMESTAMP('2020-01-01 00:00:00.000','YYYY-MM-DD HH24:MI:SS.FF'),   TO_TIMESTAMP('2017-09-28 00:00:00.000','YYYY-MM-DD HH24:MI:SS.FF'),   'SYSTEM',   NULL,   NULL );
INSERT INTO ROLE (   ROLE_ID,   ROLE_NAME,   ADMIN_FLAG,   GUEST_FLAG,   EXPIRY_DATE,   REGIST_DATE,   REGIST_PNAME,   LAST_UPDATE_DATE,   LAST_UPDATE_PNAME ) VALUES (   'DEV',   'Development Role',   '1',   '0',   TO_TIMESTAMP('2020-01-01 00:00:00.000','YYYY-MM-DD HH24:MI:SS.FF'),   TO_TIMESTAMP('2017-09-28 00:00:00.000','YYYY-MM-DD HH24:MI:SS.FF'),   'SYSTEM',   NULL,   NULL );

COMMENT;