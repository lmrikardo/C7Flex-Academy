CREATE TABLE usuario (

    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    google_id VARCHAR(100),
    nome NVARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    foto VARCHAR(500),
    role VARCHAR(30) NOT NULL,
    ativo BIT NOT NULL DEFAULT 1,
    data_cadastro DATETIME2 NOT NULL,
    ultima_alteracao DATETIME2 NOT NULL,
    ultimo_acesso DATETIME2

);

GO

CREATE TABLE curso (

    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(MAX),
    imagem VARCHAR(300),
    ativo BIT NOT NULL DEFAULT 1,
    data_cadastro DATETIME2 NOT NULL,
    ultima_alteracao DATETIME2 NOT NULL

);

GO

CREATE TABLE modulo (

    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    curso_id BIGINT NOT NULL,
    nome VARCHAR(150),
    ordem INT,
    data_cadastro DATETIME2 NOT NULL,
    ultima_alteracao DATETIME2 NOT NULL,
    CONSTRAINT fk_modulo_curso
        FOREIGN KEY(curso_id)
        REFERENCES curso(id)
);

GO

CREATE TABLE aula (

    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    modulo_id BIGINT NOT NULL,
    titulo VARCHAR(200),
    descricao VARCHAR(MAX),
    youtube_url VARCHAR(500),
    youtube_id VARCHAR(20),
    thumbnail VARCHAR(300),
    duracao INT,
    ordem INT,
    publicado BIT NOT NULL DEFAULT 1,
    data_cadastro DATETIME2 NOT NULL,
    ultima_alteracao DATETIME2 NOT NULL,

    CONSTRAINT fk_aula_modulo
        FOREIGN KEY(modulo_id)
        REFERENCES modulo(id)
);

GO

CREATE TABLE progresso (

    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    aula_id BIGINT NOT NULL,
    segundos_assistidos INT DEFAULT 0,
    concluida BIT DEFAULT 0,
    data_conclusao DATETIME2,
    CONSTRAINT fk_progresso_usuario
        FOREIGN KEY(usuario_id)
        REFERENCES usuario(id),
    CONSTRAINT fk_progresso_aula
        FOREIGN KEY(aula_id)
        REFERENCES aula(id)

);

CREATE TABLE arquivo (
    id BIGINT IDENTITY(1,1) NOT NULL,
    aula_id BIGINT NOT NULL,
    nome NVARCHAR(255) NOT NULL,
    nome_original NVARCHAR(255) NOT NULL,
    tipo NVARCHAR(100) NOT NULL,
    extensao NVARCHAR(20) NOT NULL,
    tamanho BIGINT NOT NULL,
    caminho NVARCHAR(500) NOT NULL,
    ordem INT NOT NULL DEFAULT 1,
    ativo BIT NOT NULL DEFAULT 1,
    data_cadastro DATETIME2 NOT NULL,
    ultima_alteracao DATETIME2 NOT NULL,

    CONSTRAINT PK_arquivo PRIMARY KEY (id),

    CONSTRAINT FK_arquivo_aula
        FOREIGN KEY (aula_id)
        REFERENCES aula(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_modulo_curso
ON modulo(curso_id);

CREATE INDEX idx_aula_modulo
ON aula(modulo_id);

CREATE INDEX idx_progresso_usuario
ON progresso(usuario_id);

CREATE INDEX idx_progresso_aula
ON progresso(aula_id);

CREATE INDEX IX_arquivo_aula
    ON arquivo(aula_id);