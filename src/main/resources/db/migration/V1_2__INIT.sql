INSERT INTO USER_SYSTEM (id, username,email,create_time)
VALUES ((SELECT nextval('user_id_seq')),'admin', 'admin@gmail.com', current_timestamp);