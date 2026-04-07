Discente: Arthur Vinicio da Silva Barbosa

Projeto: **Aluno Online**

    O projeto acima é um sistema de controle escolar que relaciona alunos, professores, disciplinas e notas. Através do sistema Aluno Online, o Aluno registra dados pessoais como nome e data de nascimento, recebendo um ID que o identifica de forma única no sistema.

    O mesmo acontece com o Professor, que registra seus dados pessoais e as notas dos alunos, na(s) disciplina(s) da(s) qual(is) for docente. 

**Estrutura do Projeto**

    .
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── readme.md
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── br
    │   │   │       └── com
    │   │   │           └── alunoonline
    │   │   │               └── api
    │   │   │                   ├── AlunoOnlineApplication.java
    │   │   │                   ├── controller
    │   │   │                   │   ├── AlunoController.java
    │   │   │                   │   └── ProfessorController.java
    │   │   │                   ├── model
    │   │   │                   │   ├── Aluno.java
    │   │   │                   │   └── Professor.java
    │   │   │                   ├── repository
    │   │   │                   │   ├── AlunoRepository.java
    │   │   │                   │   └── ProfessorRepository.java
    │   │   │                   └── service
    │   │   │                       ├── AlunoService.java
    │   │   │                       └── ProfessorService.java
    │   │   └── resources
    │   │       └── application.properties
    │   └── test
    │       └── java
    │           └── br.com.alunoonline.api
    │               └── com
    │                   └── alunoonline
    │                       └── api
    │                           └── AlunoOnlineApplicationTests.java
    └── target
        ├── classes
        │   ├── application.properties
        │   └── br
        │       └── com
        │           └── alunoonline
        │               └── api
        │                   ├── AlunoOnlineApplication.class
        │                   ├── controller
        │                   │   ├── AlunoController.class
        │                   │   └── ProfessorController.class
        │                   ├── model
        │                   │   ├── Aluno.class
        │                   │   └── Professor.class
        │                   ├── repository
        │                   │   ├── AlunoRepository.class
        │                   │   └── ProfessorRepository.class
        │                   └── service
        │                       ├── AlunoService.class
        │                       └── ProfessorService.class
        └── generated-sources
            └── annotations

**Detalhamento do código**

1. **Aluno**
    
    Salva informações básicas do aluno, sejam elas: id, nome, e-mail, CPF. O ID é gerado automaticamente pelo sistema com base na ordem numérica (adiciona-se 1 ao último ID gerado) e deve ser único, para garantir a integridade dos dados armazenados; nome, e-mail e CPF são fornecidos pelo usuário.

2. **Professor**

    Salva as mesmas informações coletadas do aluno (id, nome, e-mail, CPF). O ID do professor é unico assim como o ID do aluno, porém armazenado em tabela diferente daquele, de modo que um professor pode ter ID = 1 ao mesmo tempo que existe um aluno com ID = 1, já que ambos os 'ID' pertencem cada um à sua respectiva entidade. O professor também deverá ser vinculado a sua respectiva disciplina.

**Arquitetura do Projeto**

    O Aluno Online é uma API REST baseada em arquitetura em camadas, sendo uma aplicação com o framework Spring Boot, linguagem Java, com dependências gerenciadas pelo Apache Maven e utilizando PostgreSQL para persistência de dados. 

    Cada entidade do sistema possui as camadas Controller, Service, Model, Repository.

    A camada Controller é a orquestradora do sistema, pois lida diretamente com o usuário e coordena o fluxo da requisição com base nos dados fornecidos por ele, interpreta-os e aciona a próxima camada - Service, através da injeção de dependência, para que esta execute a regra de negócio determinada internamente através de seus métodos. 

    A camada Service, por sua vez, implementa as regras de negócio definidas pela equipe para o correto funcionamento do sistema. Após o devido processamento das chamadas do usuário e das regras de negócio, o Service acessa a camada Repository, que vai promover a integração do Back End com o Banco de Dados através da camada Repository.

    A camada Repository é responsável pela persistência de dados, realizando operações de CRUD no banco de dados. No projeto, ela é implementada com o Spring Data Jpa.

    Por fim, os dados são persistidos em banco de dados PostgreSQL, através das chamadas da camada Repository, que se conecta com cada entidade: alunoRepository, professorRepository, etc. 
    
**Funcionamento**

Seguem prints demonstrando funcionamento do código implementado no Insomnia, DBeaver para cada um dos métodos apresentados

1. Método **criarAluno()** - inclui um novo aluno no BD<br>

<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 09 59 14" src="https://github.com/user-attachments/assets/340b2b32-c009-4cfd-a372-410ae1c932bf" /><br>

<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 02 43" src="https://github.com/user-attachments/assets/86a55409-0ef2-485c-bcc5-6d110190f696" /><br>

2. Método **buscarTodosAlunos()** - retorna todos os alunos cadastrados<br>

<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 04 18" src="https://github.com/user-attachments/assets/0b0a2089-d0a2-4654-b4b5-5ad0940ac0e3" /><br>

3. Método **buscarAlunoPorId()** - retorna um aluno com ID específico<br>

<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 05 25" src="https://github.com/user-attachments/assets/d5b735c9-4be8-4c37-ba42-e93eb1a31e3c" /><br>

4. Método **deletarAlunoPorId()** - deleta um aluno específico do BD<br>

<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 06 49" src="https://github.com/user-attachments/assets/25b80352-2220-485b-9f56-ba9666636524" /><br>

5. Método **atualizarAlunoPorId()** - atualiza as informações de um aluno específico<br>

Antes:<br>
<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 08 11" src="https://github.com/user-attachments/assets/2067df85-b4f6-4b14-8fb6-7c3ce3a8264c" /><br>

Depois:<br>
<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 11 07" src="https://github.com/user-attachments/assets/0e50994f-bd68-4164-868c-ccfb0d1f4edd" /><br>

6. Método **criarProfessor()** - inclui um novo professor no BD<br>

Antes:<br>
<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 12 25" src="https://github.com/user-attachments/assets/0c4e920d-95ea-4dd0-8c44-847391649e91" /><br>

Depois:<br>
<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 15 53" src="https://github.com/user-attachments/assets/9ffd6b2c-e877-444e-9b54-24fce315af61" /><br>

7. Método **buscarTodosProfessores()** - retorna todos os professores cadastrados<br>

<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 16 53" src="https://github.com/user-attachments/assets/48663d4f-353a-4447-9e89-0345b6ce4033" /><br>

8. Método **buscarProfessorPorId()** - retorna um professor específico<br>

<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 17 43" src="https://github.com/user-attachments/assets/2c0ade84-9f5f-47c1-88f8-bea91508c78b" /><br>

9. Método **deletarProfessorPorId()** - deleta um professor específico<br>

Antes:<br>
<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 19 15" src="https://github.com/user-attachments/assets/fdb65f5a-5758-447d-9494-ccf955e5ab39" /><br>

Depois:<br>
<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 19 57" src="https://github.com/user-attachments/assets/b93193c1-843f-4c73-a434-ac0424e6f3cb" /><br>

10. Método **atualizarProfessorPorId()** - atualiza os dados de um professor específico<br>

Antes:<br>
<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 21 00" src="https://github.com/user-attachments/assets/c475e24e-3ce4-414d-8a12-406419ac39a6" /><br>

Depois:<br>
<img width="1920" height="1080" alt="Captura de Tela 2026-04-07 às 10 22 08" src="https://github.com/user-attachments/assets/c52b83b4-ac8b-4984-b303-418294cc628b" />
