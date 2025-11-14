CREATE TABLE usuarios (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  area_atuacao VARCHAR(100),
  nivel_carreira VARCHAR(50),
  data_cadastro DATE NOT NULL
);

CREATE TABLE trilhas (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(150) NOT NULL,
  descricao TEXT,
  nivel VARCHAR(50) NOT NULL,
  carga_horaria INT NOT NULL,
  foco_principal VARCHAR(100)
);

CREATE TABLE competencias (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  categoria VARCHAR(100),
  descricao TEXT
);

CREATE TABLE trilha_competencia (
  trilha_id BIGINT NOT NULL,
  competencia_id BIGINT NOT NULL,
  PRIMARY KEY (trilha_id, competencia_id),
  FOREIGN KEY (trilha_id) REFERENCES trilhas(id),
  FOREIGN KEY (competencia_id) REFERENCES competencias(id)
);

CREATE TABLE matriculas (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  usuario_id BIGINT NOT NULL,
  trilha_id BIGINT NOT NULL,
  data_inscricao DATE NOT NULL,
  status VARCHAR(50) NOT NULL,
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
  FOREIGN KEY (trilha_id) REFERENCES trilhas(id)
);