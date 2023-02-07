CREATE TABLE USER_SYSTEM(
                       ID BIGINT PRIMARY KEY,
                       USERNAME VARCHAR(30) NOT NULL UNIQUE,
                       EMAIL VARCHAR(255) NOT NULL UNIQUE,
                       CREATE_TIME TIMESTAMP NOT NULL
);

CREATE TABLE PAUTA(
                      ID BIGINT PRIMARY KEY,
                      TITULO VARCHAR(500) NOT NULL ,
                      DESCRICAO VARCHAR ,
                      DATA_CRIACAO TIMESTAMP NOT NULL
);

CREATE TABLE SESSAO_VOTACAO(
                               ID BIGINT PRIMARY KEY,
                               PAUTA_ID BIGINT NOT NULL,
                               DATA_ABERTURA TIMESTAMP NOT NULL,
                               DATA_ENCERRAMENTO TIMESTAMP NOT NULL,
                               FOREIGN KEY(PAUTA_ID) REFERENCES PAUTA(ID)
);

CREATE TABLE VOTO(
                     ID BIGINT PRIMARY KEY,
                     USER_ID BIGINT NOT NULL,
                     PAUTA_ID BIGINT NOT NULL,
                     TIPO_VOTO VARCHAR(3) NOT NULL,
                     DATA_CRIACAO TIMESTAMP NOT NULL,
                     FOREIGN KEY(USER_ID) REFERENCES USER_SYSTEM(ID),
                     FOREIGN KEY(PAUTA_ID) REFERENCES PAUTA(ID)
);