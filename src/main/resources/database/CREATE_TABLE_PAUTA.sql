CREATE TABLE PAUTA(
                        ID BIGINT PRIMARY KEY,
                        TITULO VARCHAR(255) NOT NULL ,
                        DESCRICAO VARCHAR ,
                        DATA_CRIACAO TIMESTAMP NOT NULL
)
CREATE SEQUENCE pauta_id_seq
    START WITH 1
    INCREMENT BY 1