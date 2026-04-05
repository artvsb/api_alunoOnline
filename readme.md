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

Segue vídeo demonstrando funcionamento do código implementado no Insomnia, DBeaver

<video controls src="Gravação de Tela 2026-04-05 às 12.24.32.mov" title="Title"></video>