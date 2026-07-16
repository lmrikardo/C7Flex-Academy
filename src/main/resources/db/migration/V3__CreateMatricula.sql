CREATE TABLE matricula
(
    id BIGINT IDENTITY(1,1) NOT NULL,

    usuario_id BIGINT NOT NULL,

    curso_id BIGINT NOT NULL,

    status VARCHAR(20) NOT NULL,

    data_matricula DATETIME2 NOT NULL,

    data_cancelamento DATETIME2 NULL,

    CONSTRAINT PK_matricula
        PRIMARY KEY (id),

    CONSTRAINT FK_matricula_usuario
        FOREIGN KEY (usuario_id)
            REFERENCES usuario(id),

    CONSTRAINT FK_matricula_curso
        FOREIGN KEY (curso_id)
            REFERENCES curso(id)
);

CREATE INDEX IDX_matricula_usuario
    ON matricula(usuario_id);

CREATE INDEX IDX_matricula_curso
    ON matricula(curso_id);

CREATE INDEX IDX_matricula_status
    ON matricula(status);

ALTER TABLE matricula
    ADD CONSTRAINT UK_matricula_usuario_curso
        UNIQUE(usuario_id, curso_id);