CREATE TABLE "SESSAO_VOTACAO"(
                                 ID BIGINT PRIMARY KEY,
                                 PAUTA_ID BIGINT NOT NULL,
                                 DATA_ABERTURA TIMESTAMP NOT NULL,
                                 DATA_CRIACAO TIMESTAMP NOT NULL,
                                 DATA_ENCERRAMENTO TIMESTAMP NOT NULL,
                                 FOREIGN KEY(PAUTA_ID) REFERENCES "PAUTA"(ID)
);

CREATE SEQUENCE sessao_votacao_id_seq START WITH 1 INCREMENT BY 1;