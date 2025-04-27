import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import services.ATMService;

public class ATMInterface {
    private ATMService atmService;
    private JFrame frame;

    public ATMInterface(ATMService atmService) {
        this.atmService = atmService;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Simulador de Caixa Eletrônico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        showLoginScreen();
    }

    private void showLoginScreen() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel userLabel = new JLabel("Usuário:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Senha:");
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Cadastrar");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(loginButton);
        panel.add(registerButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if (atmService.login(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login bem-sucedido!");
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Credenciais inválidas. Tente novamente.");
            }
        });

        registerButton.addActionListener(e -> showRegistrationScreen());
    }

    private void showRegistrationScreen() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JLabel userLabel = new JLabel("Usuário:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Senha:");
        JPasswordField passField = new JPasswordField();
        JLabel depositLabel = new JLabel("Depósito Inicial:");
        JTextField depositField = new JTextField();
        JButton registerButton = new JButton("Cadastrar");
        JButton backButton = new JButton("Voltar");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(depositLabel);
        panel.add(depositField);
        panel.add(registerButton);
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        registerButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            double initialDeposit;
            try {
                initialDeposit = Double.parseDouble(depositField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Depósito inválido.");
                return;
            }

            if (atmService.registerUser(username, password, initialDeposit)) {
                JOptionPane.showMessageDialog(frame, "Cadastro realizado com sucesso!");
                showLoginScreen();
            } else {
                JOptionPane.showMessageDialog(frame, "Usuário já existe. Tente novamente.");
            }
        });

        backButton.addActionListener(e -> showLoginScreen());
    }

    private void showMainMenu() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JButton withdrawButton = new JButton("Saque");
        JButton depositButton = new JButton("Depósito");
        JButton balanceButton = new JButton("Consulta de Saldo");
        JButton logoutButton = new JButton("Logout");

        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(balanceButton);
        panel.add(logoutButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        withdrawButton.addActionListener(e -> showWithdrawScreen());
        depositButton.addActionListener(e -> showDepositScreen());
        balanceButton.addActionListener(e -> showBalanceScreen());
        logoutButton.addActionListener(e -> {
            atmService.getLoggedInUser(); // Logout
            JOptionPane.showMessageDialog(frame, "Logout realizado com sucesso!");
            showLoginScreen();
        });
    }

    private void showWithdrawScreen() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JTextField amountField = new JTextField();
        JButton withdrawButton = new JButton("Confirmar Saque");

        panel.add(new JLabel("Valor do Saque:"));
        panel.add(amountField);
        panel.add(withdrawButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        withdrawButton.addActionListener(e -> {
            double amount;
            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Valor inválido.");
                return;
            }

            atmService.withdraw(amount);
            JOptionPane.showMessageDialog(frame, "Saque realizado com sucesso!");
            showMainMenu();
        });
    }

    private void showDepositScreen() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JTextField amountField = new JTextField();
        JButton depositButton = new JButton("Confirmar Depósito");

        panel.add(new JLabel("Valor do Depósito:"));
        panel.add(amountField);
        panel.add(depositButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        depositButton.addActionListener(e -> {
            double amount;
            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Valor inválido.");
                return;
            }

            atmService.deposit(amount);
            JOptionPane.showMessageDialog(frame, "Depósito realizado com sucesso!");
            showMainMenu();
        });
    }

    private void showBalanceScreen() {
        double balance = atmService.checkBalance();
        JOptionPane.showMessageDialog(frame, "Seu saldo é: " + balance);
        showMainMenu();
    }

    public void start() {
        frame.setVisible(true);
    }
}