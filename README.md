# DESAFIO PISMO -  ROTINAS TRANSACIONAIS

## Descrição do Sistema

O sistema foi desenvolvido com o intuido de viabilizar a gestão de rotinas transacionais, nesta versão estamos contemplando as seguintes funcionalidades:

###  <span style="color: green;">&#10004;</span>  Criação de Conta

Os usuários podem criar uma conta no sistema, fornecendo o núumero do CPF.


###  <span style="color: green;">&#10004;</span> Consultar Informações de uma Conta

Os usuários podem consultar as informações de uma conta específica, para isso é necessário que o cliente informe apenas o ID da conta.

###  <span style="color: green;">&#10004;</span> Realizar Transações

O sistema suporta quatro tipos de transações:

- __CASH_PURCHASE__: Compra à vista.
- __INSTALLMENT_PURCHASE__: Compra parcelada.
- __WITHDRAWAL__: Saque.
- __PAYMENT__: Pagamento.

Cada transação é registrada na conta do usuário correspondente e atualiza o saldo da conta de acordo com o tipo de transação.

## __Informações__ __importantes__ ##

&#8594; Estamos disponibilizando uma collection contendo as operações disponíveis nas APIS presentes na aplicação, podendo ser fácilmente importada no POSTMAN.


## Excução do Projeto

Para realizar a execução do projeto basta realizar os passos abaixo:

   - 1. __CLONAR O REPOSITÓRIO__: https://github.com/aleffchaves/transactions-service.git
   - 3. __EXECUTAR O COMANDO:__  docker-compose up 
    
   - __OBS:__ Caso não tenha o docker instalado a aplicação pode ser executada normalmente de como um app Spring qualquer
