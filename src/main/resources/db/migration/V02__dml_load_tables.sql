INSERT INTO usuario.endereco (
    rua, numero, cep, complemento, bairro, cidade
) VALUES
    ('Rua das Palmeiras', 123, '12345678', 'Apto 101', 'Centro', 'São Paulo'),
    ('Avenida Brasil', 456, '87654321', NULL, 'Jardins', 'Rio de Janeiro'),
    ('Rua Sete de Setembro', 789, '11223344', 'Casa 2', 'Boa Vista', 'Belo Horizonte');

-- Pacientes
INSERT INTO usuario.paciente (
    cpf, nome, email, login, senha, genero, telefone, data_nascimento, endereco_id
) VALUES (
    '12345678901', 'Maria Silva', 'maria.silva@example.com', 'mariasilva', 'senha123',
    'F', '(11) 91234-5678', '1990-03-15',
    (SELECT id FROM usuario.endereco WHERE rua = 'Rua das Palmeiras' AND numero = 123 LIMIT 1)
);

INSERT INTO usuario.paciente (
    cpf, nome, email, login, senha, genero, telefone, data_nascimento, endereco_id
) VALUES (
    '09876543210', 'João Souza', 'joao.souza@example.com', 'joaosouza', 'senha456',
    'M', '(21) 99876-5432', '1988-07-20',
    (SELECT id FROM usuario.endereco WHERE rua = 'Avenida Brasil' AND numero = 456 LIMIT 1)
);

INSERT INTO usuario.paciente (
    cpf, nome, email, login, senha, genero, telefone, data_nascimento, endereco_id
) VALUES (
    '11223344556', 'Ana Lima', 'ana.lima@example.com', 'analima', 'senha789',
    'F', '(31) 98765-4321', '1995-11-05',
    (SELECT id FROM usuario.endereco WHERE rua = 'Rua Sete de Setembro' AND numero = 789 LIMIT 1)
);

INSERT INTO usuario.anamnese (
    id, fumante, gravida, diabetico, hipertenso, data_criacao, data_ultima_alteracao
) VALUES
(gen_random_uuid(), TRUE, FALSE, TRUE, FALSE, now(), NULL),
(gen_random_uuid(), FALSE, TRUE, FALSE, TRUE, now(), now()),
(gen_random_uuid(), FALSE, FALSE, TRUE, TRUE, now(), now());