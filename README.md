# 🌐 Global Solution 2025 – Plataforma de Upskilling/Reskilling para o Futuro do Trabalho

API construída em **Java 17 + Spring Boot 3.2**, projetada para apoiar profissionais na requalificação contínua rumo ao mercado de trabalho 2030+.  
A solução disponibiliza trilhas de aprendizagem, competências futuras (IA, Dados, Empatia), gestão de usuários e toda a lógica necessária para inscrição, validação e persistência dos dados.

---

## 🚩 Visão Geral do Projeto
A plataforma endereça o impacto da automação e da IA no trabalho, oferecendo um ecossistema escalável e organizado para formação contínua.

**Principais capacidades:**
- CRUD completo de **Usuários** e **Trilhas**
- Inscrição em trilhas focadas em competências do futuro
- Seeds automáticos (usuários, trilhas, competências)
- Validações sólidas e tratamento centralizado de erros
- Arquitetura limpa: **Controller → Service → Repository**

---

## 🌍 Alinhamento com os ODS (ONU)

**ODS 4 – Educação de Qualidade**  
Garante acesso contínuo a trilhas de aprendizagem estruturadas e atualizadas.

**ODS 8 – Trabalho Decente e Crescimento Econômico**  
Forma profissionais para empregos emergentes e mitiga impactos da automação.

**ODS 9 – Indústria, Inovação e Infraestrutura**  
Promove competências tecnológicas essenciais para transformação digital.

**ODS 10 – Redução das Desigualdades**  
Democratiza o acesso à educação técnica independente da condição social.

---

## 🛠️ Tecnologias
- **Java 17**
- **Spring Boot 3.2.0**
- **Oracle Database** (H2 opcional para uso local)
- **Maven**
- **JUnit 5 + Mockito**

**Dependências-chave:**
- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-validation`
- `ojdbc11`
- `spring-boot-starter-test`

---

## 🚀 Executando o Projeto

### 1. Clone o repositório
```bash
git clone https://github.com/JeannMatheuss/GS-JAVA.git
cd GS-JAVA
```

### 2. Configure o Banco de Dados Oracle
```bash
export ORACLE_URL=jdbc:oracle:thin:@localhost:1521:xe
export ORACLE_USER=seu_usuario
export ORACLE_PASSWORD=sua_senha
```

### 3. Execute
```bash
mvn clean install
mvn spring-boot:run
```

---

## 📊 Exemplos de Requisições (JSON)

Use Postman/Insomnia para testar. Base URL: http://localhost:8080/api/.

### 1. Criar Usuário (POST /api/usuarios)
```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "senha123"
}
```

Resposta Esperada: Status 201, JSON com ID gerado.

### 2. Listar Todas as Trilhas (GET /api/trilhas)

Resposta Esperada: Status 200, array de trilhas (ex.: [{`"id":1, "nome":"Trilha de IA", "nivel":"INICIANTE", "cargaHoraria":40}`]).

### 3. Buscar Trilha por ID (GET /api/trilhas/1)

Resposta Esperada: Status 200, detalhes da trilha ou 404 se não encontrado.

### 4. Atualizar Usuário (PUT /api/usuarios/1)
```json
{
  "nome": "João Silva Atualizado",
  "email": "joao@email.com",
  "senha": "novaSenha123"
}
```

Resposta Esperada: Status 200, JSON atualizado.

### 5. Deletar Trilha (DELETE /api/trilhas/1)

Resposta Esperada: Status 204 (sem corpo).

---

## 🧪 Como Testar

- **Testes Unitários:** Execute mvn test – cobre controllers, services e validações (ex.: cenários de sucesso, erro 404, validação falha).
  
- **Testes Manuais com Postman:**
  
 -- Importe uma collection (crie endpoints acima).
 
 -- Teste validações: Envie JSON inválido (ex.: email sem @) → Status 400 com mensagens de erro.
 
 -- Teste exceções: Busque ID inexistente → Status 404.
   
- **Cenários de Teste:**
  
 -- CRUD completo para Usuário e Trilha.
 
 -- Seeds carregados: Verifique se usuários/trilhas iniciais existem via GET.
 
 -- Validações: Nome obrigatório, email válido, carga horária >0.

---

## 📁 Estrutura do Projeto
```bash
src/
├── main/
│   ├── java/com/globalsolution/javags/
│   │   ├── config/          # DataLoader (seeds)
│   │   ├── controller/      # TrilhaController, UsuarioController
│   │   ├── dto/             # TrilhaDTO, UsuarioDTO
│   │   ├── entity/          # Usuario, Trilha, Competencia, Matricula
│   │   ├── exception/       # Exceções customizadas e GlobalExceptionHandler
│   │   ├── repository/      # Interfaces JPA
│   │   ├── service/         # TrilhaService, UsuarioService
│   │   └── GsJavaApplication.java
│   └── resources/
│       ├── application.properties  # Configuração do banco
│       └── db/                     # Scripts SQL (schema.sql, data.sql)
└── test/
    └── java/com/globalsolution/javags/  # Testes unitários
```

---

## 🤝 Como Contribuir

### 1. Fork o repositório.
### 2. Crie uma branch para sua feature: git checkout -b feature/nova-funcionalidade.
### 3. Commit suas mudanças: git commit -m "Adiciona nova funcionalidade".
### 4. Push e abra um Pull Request.
### 5. Siga boas práticas: Adicione testes para novas funcionalidades e documente mudanças no README.

---

## Integrantes

Jean Matheus Mohamed de Oliveira

Pedro Henrique Ribeiro Sampaio
