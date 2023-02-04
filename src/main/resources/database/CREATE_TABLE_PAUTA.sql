CREATE TABLE "VOTO"(
                       ID BIGINT PRIMARY KEY,
                       USER_ID BIGINT NOT NULL,
                       PAUTA_ID BIGINT NOT NULL,
                       TIPO_VOTO VARCHAR(3) NOT NULL,
                       DATA_CRIACAO TIMESTAMP NOT NULL,
                       FOREIGN KEY(USER_ID) REFERENCES "USER"(ID),
                       FOREIGN KEY(PAUTA_ID) REFERENCES "PAUTA"(ID)
);

CREATE SEQUENCE voto_id_seq START WITH 1 INCREMENT BY 1;