CREATE SCHEMA IF NOT EXISTS usuario;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE usuario.endereco (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    rua VARCHAR(255) NOT NULL,
    numero INTEGER NOT NULL,
    cep VARCHAR(8) NOT NULL,
    complemento VARCHAR(255),
    bairro VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_ultima_alteracao TIMESTAMP
);

CREATE TABLE usuario.paciente (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    genero VARCHAR(1) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    endereco_id  UUID NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_ultima_alteracao TIMESTAMP,

    CONSTRAINT fk_paciente_endereco FOREIGN KEY (endereco_id )
        REFERENCES usuario.endereco(id)
);

