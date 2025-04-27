
# ATM Simulator

esse projeto é um simulador de caixa eletrônico (ATM) em Java. Ele permite que múltiplos usuários realizem operações bancárias como login, saque, depósito e consulta de saldo. Os dados dos usuários são armazenados em um arquivo de texto.

## Estrutura do Projeto

```
ATM-Simulator
├── src
│   ├── Main.java
│   ├── models
│   │   ├── Account.java
│   │   └── User.java
│   ├── services
│   │   ├── ATMService.java
│   │   └── FileStorageService.java
│   ├── utils
│   │   └── InputValidator.java
│   └── menu
│       └── MenuHandler.java
├── data
│   └── users.txt
├── .gitignore
└── README.md
```

## Funcionalidades

- **Login**: Permite que os usuários façam login usando suas credenciais.
- **Saque**: Usuários podem realizar saques de suas contas.
- **Depósito**: Usuários podem depositar dinheiro em suas contas.
- **Consulta de Saldo**: Usuários podem verificar o saldo de suas contas.

## Como Executar

1. Certifique-se de ter o Java instalado em sua máquina.
2. Clone este repositório ou faça o download do código.
3. Navegue até o diretório `src`.
4. Compile o projeto usando o comando:
   ```
   javac Main.java
   ```
5. Execute o simulador com o comando:
   ```
   java Main
   ```

## Armazenamento de Dados

Os dados dos usuários são armazenados no arquivo `data/users.txt`. Cada linha contém informações sobre um usuário, incluindo nome, senha e saldo da conta.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.
