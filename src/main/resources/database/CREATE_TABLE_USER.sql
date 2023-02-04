CREATE TABLE USER_SYSTEM(
                       ID BIGINT PRIMARY KEY,
                       USERNAME VARCHAR(30) NOT NULL UNIQUE,
                       EMAIL VARCHAR(255) NOT NULL UNIQUE,
                       CREATE_TIME TIMESTAMP NOT NULL
);

CREATE SEQUENCE user_id_seq START WITH 1 INCREMENT BY 1;