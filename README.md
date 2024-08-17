## Link para o vídeo completo: 

[Assista ao vídeo](https://www.youtube.com/watch?v=dttXo48oXt4)

## Repositório com o código do vídeo:
[buildrun-desafio-backend-picpay](https://github.com/buildrun-tech/buildrun-desafio-backend-picpay)

> **Desafio:** [PicPay Desafio Backend](https://github.com/PicPay/picpay-desafio-backend)



## Resolveremos o Desafio Backend do PicPay e aprenderemos:

- Como criar uma API com Spring Boot
- Como mapear as entidades com Jakarta Persistence
- Como comunicar com o banco de dados MySQL via Docker
- Como comunicar com API externa via Spring Cloud OpenFeign
- Como realizar processamento assíncrono com CompletableFuture
- Como validar dados de entrada da API com Hibernate Validator
- Como tratar as exceções de sua API com Problem Details (RFC 7807)
- Como efetuar logs com o Log4J

## Lista de tarefas:

### Introdução:

- [x] Qual é o desafio que vamos resolver?

---

### Comunicação com o banco e dados:

- [x] Iniciando o projeto Java (JPA, MySQL, Validator, OpenFeign)
- [x] Configurando o MySQL no Docker
- [x] Configurando a comunicação do Spring Boot com o MySQL

---

### Funcionalidade de Cadastro de Wallet:

- [x] Mapear as entidades (Wallet, WalletType)
- [x] Criar inicialização de WalletType no banco de dados
- [x] Criar a funcionalidade de cadastro de Wallet
  - 1° Validação dos dados únicos
  - 2° Inserção na base
- [x] Criar tratamento de exceções com ProblemDetails e RestExceptionHandler
- [x] Criar validação de dados de entrada
- [x] Ajustar tratamento de exceções
- [x] Testar a API e validar no banco de dados

---

### Funcionalidade de Transferência:

- [x] Mapear a entidade (Transaction)
- [x] Criar os mocks do Autorizador e Notificador Externo ([Designer Mocky](https://designer.mocky.io/))
- [x] Criar o serviço que irá consultar o autorizador externo
- [x] Criar o serviço que irá notificar o recebimento da transferência
- [x] Criar a funcionalidade de transação
  - 1° Validar o tipo da carteira
  - 2° Validar o saldo
  - 3° Consultar autorizador externo
  - 4° Realizar a transferência
  - 5° Enviar notificação para o recebedor (CompletableFuture)
- [x] Validar o tratamento de exceções com RestExceptionHandler
- [x] Testar a API e validar no banco de dados

---

### Ajuste final:

- [x] Aplicar logging no projeto com SLF4J
