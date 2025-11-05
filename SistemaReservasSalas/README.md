## Sistema de Reserva de Salas - ReuniPe
### DESCRIÇÃO DO PROJETO
Sistema de reserva de salas de estudo em uma instituição de ensino, desenvolvido para aplicar os conceitos de Programação Orientada a Objetos e integração com banco de dados MySQL. O sistema permite a criação de reservas, oferecendo uma solução organizada para o controle de espaços físicos em ambiente educacional.

### OBJETIVO
Desenvolver uma aplicação robusta que integre conceitos avançados de POO com persistência de dados, seguindo padrões de arquitetura e boas práticas de desenvolvimento.

### TECNOLOGIAS UTILIZADAS
- Java - Linguagem de programação principal

- MySQL - Sistema gerenciador de banco de dados

- JDBC - Conector para integração Java/MySQL(Arquivo localizado na pasta lib)

- MySQL Workbench - Modelagem de dados

- VS Code - Ambiente de desenvolvimento

### ARQUITETURA DO SISTEMA
##### Padrões Implementados

- DAO Pattern (Data Access Object) - Para abstração e encapsulamento do acesso aos dados

- Rich Domain Model - Models com lógica de negócio incorporada

- Transaction Script - Lógica principal centralizada na classe Main

##### Estrutura em Camadas
1. Data Access Layer (pacote dao) - Responsável pela persistência

2. Business Layer (pacotes model + main) - Lógica de negócio e entidades

##### Evolução Arquitetural Planejada
1. Implementação de Service Layer

2. Desenvolvimento de Presentation Layer (UI)

3. Separação de Controller da classe Main

### ESTRUTURA DO PROJETO

SistemaReservasSalas/

    ├── src/                    # Código fonte
    │   ├── com/reunipe/
    │   │   ├── model/         # Entidades (Usuario, Sala, Reserva)
    │   │   ├── dao/           # Acesso a dados
    │   │   ├── enums/         # Enumeradores
    │   │   └── main/          # Classe principal
    ├── bin/                   # Arquivos compilados
    └── lib/                   # Dependências                                 

### COMO EXECUTAR
#### Pré-requisitos:
- Java JDK 8 ou superior

- MySQL Server

- MySQL Connector/J

###### Extensões: 
1. Extension Pack for Java 
2. MySQL(database-client.com)
3. Markdown Preview Enhanced

#### Configuração e Execução

1. Configurar Banco de Dados:

- Instalar e configurar MySQL Server

- Executar script de criação das tabelas

2. Compilar Projeto:
- Opção 1: Compilar Arquivo por Arquivo (Mais Segura)
  #### vá para pasta do sistema:
        cd C:\Users\Digite o nome da sua conta local\Desktop\ReuniPE\SistemaReservasSalas

  #### compile todos os arquivo em um só comando:
        javac -d bin ^
          src/com/reunipe/database/ConexaoBD.java ^
          src/com/reunipe/enums/*.java ^
          src/com/reunipe/model/*.java ^
          src/com/reunipe/dao/*.java ^
          src/com/reunipe/main/Main.java

  - Opção 2: Usar Wildcard Simples:
  
          javac -d bin src/com/reunipe/main/*.java src/com/reunipe/model/*.java src/com/reunipe/dao/*.java src/com/reunipe/enums/*.java src/com/reunipe/database/*.java
                            

se por acaso você for utilizar outra senha diferente de "root"
não se esqueça de modificar no código conexãoBD, e em 
seguida compilar o código.

3. Executar Aplicação

        java -cp "bin;lib/mysql-connector-j-9.5.0.jar" com.reunipe.main.Main

### MODELO DE DADOS

#### Tabelas Principais

- usuarios - Armazena dados dos usuários do sistema

- salas - Gerencia informações das salas disponíveis

- reservas - Registra histórico de reservas realizadas

- administradores - Controla acesso administrativo

### DESAFIOS E APRENDIZADOS

#### Desafios Enfrentados

1. Definição de Atributos e Métodos: Dificuldade inicial Durante o projeto, obtive dificuldade para identificar quais atributos e metodos seriam utilisados nas classes e como tudo isso ficaria implementado no resultado final, para isso pesquisei referencias de projetos similares onde eu adquirir uma noção maior do projeto, algumas dessas pesquisas inclui documentos pdf e video aulas.
#####
2. Estruturação de Projeto: dificuldade para entender como ficaria a criação deste projeto, quais pasta criar e quais seriam suas funções, neste momento a programação vai além de código, consiste em entender composições de arquiteturas e construção padrão de projetos, e como será incluido
Integração de Camadas: Conexão eficiente entre lógica de negócio e persistência de dados.

### Abordagem de Solução
Para superar os desafios, foram realizadas pesquisas em:

- Documentações técnicas em PDF

- Videoaulas especializadas

- Fóruns e comunidades (Reddit, StackOverflow)

### Aprendizado

Apesar das dificuldades não olhei este projeto de forma negativa, pelo contrário acredito que me ajudou muito no meu aprendizado, e sinto  que posso melhorar muito ainda, após entender conceitos simples na programação que antes para mim era abstrato.

### Situação Análoga Superada
Eu experenciei uma vez em um processo seletivo, onde tive de fazer 
um projeto de gestão de produtos(CRUD) no qual eu falhei,
o projeto tinha de usar tecnologias como JavaScript,
node.js, Vue.js e firebase/firestore como "NoSQL", falhei neste projeto por ser iniciante e não entender coisas básicas da programação que vão além lógica de programção, estruturas de dados e interface web.

### FONTES DE PESQUISA:
##### Documentações PDF encontrada na internet, que me ajudou a entender um pouco como se deve ser feito um projeto de resevar salas:
1. https://memoriajornada.ifsuldeminas.edu.br/index.php/jcmch4/jcmch4/paper/viewFile/3025/2212

2. https://www.cin.ufpe.br/~if716/projetos/2016-2/Equipe2.2.pdf

3. https://ric.cps.sp.gov.br/bitstream/123456789/8450/1/Sistema%20Online%20para%20%20Reservas%20de%20Salas%20e%20Laborat%C3%B3rios.pdf

##### Criação do diagrama de classes(UML) Conteúdo teórico e prático:

1. https://www.youtube.com/watch?v=C3xYBT3o_5k&list=PLucm8g_ezqNqCRGHGHoacCo6N1bfN7hXZ

2. https://www.youtube.com/watch?v=srA9jiCxfj4

3. https://www.youtube.com/watch?v=lBMZt-UTUnI

##### Banco de dados:

1. https://youtu.be/IgO6L7jXbxY?si=nFzMwTu391xoRwHo

2. https://www.youtube.com/watch?v=FQ4rbt6pTeM

3. https://www.youtube.com/watch?v=5eR6YbhH4Yg

##### Conexão com o Banco de dados:

1. https://www.youtube.com/watch?v=70s7JsiaBRc

##### Outras fontes:

Inclui Forums e comunidades como Redit e StackOverflow.

### CRONOGRAMA DE DESENVOLVIMENTO
##### Etapa	Data   Atividade
-	31/10/2025	Diagrama de Classes (UML)
-	01/11/2025	Modelo Físico de Dados (MySQL)
-	02/11/2025	Desenvolvimento Java
-	03/11/2025	Documentação e Descrição no Github

###### Duração Total: 4 dias, no início decidi que faria cada parte sem pressa, A etapa inicial consistiu na elaboração do diagrama UML, que serviu como base para toda a estrutura subsequente.

###### Em seguida, partiu-se para a criação do modelo físico de dados, derivado diretamente do diagrama de classes. Com base nesse modelo, foi gerado o script SQL necessário para a implementação do banco de dados.

###### Conforme o desenvolvimento avançava especialmente durante a codificação em Java, ajustes pontuais foram realizados no script SQL, garantindo maior precisão e alinhamento com as necessidades da aplicação.

###### Por fim, dedicou-se à integração do projeto como um todo e à documentação final, assegurando que todas as etapas estivessem devidamente registradas e organizadas.

### MELHORIAS FUTURAS
1. Funcionalidades Planejadas Operações CRUD completas e intuitivas

2. Interface gráfica para automação de funções

3. Desenvolvimento de interface web responsiva

### CONCLUSÃO

###### Apesar de eu já ter conhecimento prévio de alguns asuntos, enfrentei alguns obstaculos que virou um aprendizado, onde eu não só pratiquei como também estudei e revisei conteúdos de forma teórica que não via à algum tempo.

##### Desenvolvedor: Leonardo Correia de Aguiar
##### Orientador: Professor Hugo Leonardo
##### Disciplina: Programação Orientada a Objetos (POO)
##### Instituição: UNIBRA - Centro Universitário Brasileiro
##### Período: 31/10/2025 a 03/11/2025

###### Projeto acadêmico desenvolvido para fins educacionais

