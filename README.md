# DESAFIO PISMO - ROTINAS TRANSACIONAIS

## Descrição do Sistema

Este sistema foi desenvolvido com o propósito de facilitar a gestão de rotinas transacionais. Nesta versão, oferecemos as seguintes funcionalidades:

### ✔️ Criação de Conta

Os usuários podem criar uma conta no sistema fornecendo seu número de CPF.

### ✔️ Consulta de Informações de uma Conta (Para executar esta operação é necessário realizar a criação de uma conta)

Os usuários podem consultar informações de uma conta específica, bastando informar o ID da conta.

### ✔️ Realização de Transações (Para executar esta operação é necessário realizar a criação de uma conta)

O sistema suporta quatro tipos de transações:

- **CASH_PURCHASE**: Compra à vista.
- **INSTALLMENT_PURCHASE**: Compra parcelada.
- **WITHDRAWAL**: Saque.
- **PAYMENT**: Pagamento.

Cada transação é registrada na conta do usuário correspondente e atualiza o saldo da conta de acordo com o tipo de transação.

## Informações Importantes

➡️ Disponibilizamos uma collection das APIs contendo todas as operações disponíveis na aplicação. Essa collection está disponível na raiz do projeto.
- Fique a vontade para explora-la 

## Execução do Projeto

Para executar o projeto, siga os passos abaixo:

1. **CLONE O REPOSITÓRIO**: [https://github.com/aleffchaves/transactions-service.git](https://github.com/aleffchaves/transactions-service.git)

2. **EXECUTE O COMANDO**: `docker build -t transactions-app .`
3. **EXECUTE O COMANDO**: `docker-compose up`

4. **ACESSO AO SWAGGER**: [http://localhost:8080/transactions/swagger-ui/index.html](http://localhost:8080/transactions/swagger-ui/index.html)

Observações:

- Ao executar o `docker-compose`, um banco de dados MySQL será inicializado automaticamente.
- Caso você não tenha o Docker instalado, a aplicação pode ser executada como um aplicativo Spring convencional.
