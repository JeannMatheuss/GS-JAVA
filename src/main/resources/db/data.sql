-- Competências do futuro
INSERT INTO competencias (nome, categoria, descricao) VALUES ('Inteligência Artificial', 'Tecnológica', 'Habilidades em IA para automação e análise de dados.');
INSERT INTO competencias (nome, categoria, descricao) VALUES ('Empatia e Colaboração', 'Humana', 'Competências interpessoais para trabalho híbrido.');
INSERT INTO competencias (nome, categoria, descricao) VALUES ('Análise de Dados', 'Tecnológica', 'Processamento e interpretação de big data.');

-- Trilhas pré-cadastradas
INSERT INTO trilhas (nome, descricao, nivel, carga_horaria, foco_principal) VALUES ('Trilha de IA para Profissionais', 'Aprenda IA para requalificação em 2030.', 'INICIANTE', 40, 'Tecnologia');
INSERT INTO trilhas (nome, descricao, nivel, foco_principal) VALUES ('Trilha de Soft Skills', 'Desenvolva empatia e colaboração.', 'INTERMEDIARIO', 30, 'Humano');

-- Relacionamento trilha-competencia
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (1, 1);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (1, 3);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (2, 2);

-- Usuários de exemplo
INSERT INTO usuarios (nome, email, area_atuacao, nivel_carreira, data_cadastro) VALUES ('João Silva', 'joao@email.com', 'TI', 'Júnior', '2023-10-01');
INSERT INTO usuarios (nome, email, area_atuacao, nivel_carreira, data_cadastro) VALUES ('Maria Santos', 'maria@email.com', 'RH', 'Pleno', '2023-10-02');

-- Matrículas de exemplo
INSERT INTO matriculas (usuario_id, trilha_id, data_inscricao, status) VALUES (1, 1, '2023-10-05', 'ATIVA');
INSERT INTO matriculas (usuario_id, trilha_id, data_inscricao, status) VALUES (2, 2, '2023-10-06', 'ATIVA');