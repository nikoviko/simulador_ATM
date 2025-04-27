package menu;

import java.util.Scanner;
import services.ATMService;

public class MenuHandler {
    private Scanner scanner;
    private ATMService atmService;

    public MenuHandler(ATMService atmService) {
        this.atmService = atmService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("Bem-vindo ao Simulador de Caixa Eletrônico");
            System.out.println("1. Login");
            System.out.println("2. Cadastrar-se");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    handleRegistration();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void handleLogin() {
        System.out.print("Nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        if (atmService.login(username, password)) {
            displayUserMenu();
        } else {
            System.out.println("Login falhou. Verifique suas credenciais.");
        }
    }

    private void handleRegistration() {
        System.out.print("Escolha um nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Escolha uma senha: ");
        String password = scanner.nextLine();
        System.out.print("Depósito inicial: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer

        if (atmService.registerUser(username, password, initialDeposit)) {
            System.out.println("Cadastro realizado com sucesso! Agora você pode fazer login.");
        } else {
            System.out.println("Erro: Nome de usuário já existe. Tente novamente.");
        }
    }

    private void displayUserMenu() {
        while (true) {
            System.out.println("Menu do Usuário");
            System.out.println("1. Saque");
            System.out.println("2. Depósito");
            System.out.println("3. Consulta de Saldo");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (choice) {
                case 1:
                    handleWithdrawal();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleBalanceCheck();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void handleWithdrawal() {
        System.out.print("Valor do saque: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer
        atmService.withdraw(amount);
    }

    private void handleDeposit() {
        System.out.print("Valor do depósito: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer
        atmService.deposit(amount);
    }

    private void handleBalanceCheck() {
        double balance = atmService.checkBalance();
        System.out.println("Seu saldo é: " + balance);
    }

}