package services;

import models.Account;
import models.User;
import java.util.List;
import java.util.ArrayList;

public class ATMService {
    private List<User> users;
    private User loggedInUser;

    public ATMService() {
        this.users = new ArrayList<>();
        loadUsers();
    }

    private void loadUsers() {
        FileStorageService fileStorage = new FileStorageService();
        this.users = fileStorage.loadUsers();
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                return true;
            }
        }
        return false;
    }

    public boolean registerUser(String username, String password, double initialDeposit) {
        // verifica se o nome de usuário ja existe
        for (User user : users) {
            if (user.getName().equals(username)) {
                return false; // nome de usuário ja existe
            }
        }

        // criar novo usuário
        Account account = new Account("0000", initialDeposit); // numero ficticio
        User newUser = new User(username, password, account);
        users.add(newUser);

        // salva no arquivo
        updateUserData();
        return true;
    }

    public void deposit(double amount) {
        if (loggedInUser != null) {
            Account account = loggedInUser.getAccount();
            account.deposit(amount);
            updateUserData();
        }
    }

    public void withdraw(double amount) {
        if (loggedInUser != null) {
            Account account = loggedInUser.getAccount();
            if (account.getBalance() >= amount) {
                account.withdraw(amount);
                updateUserData();
            } else {
                System.out.println("Saldo insuficiente.");
            }
        }
    }

    public double checkBalance() {
        if (loggedInUser != null) {
            return loggedInUser.getAccount().getBalance();
        }
        return 0;
    }

    private void updateUserData() {
        FileStorageService fileStorage = new FileStorageService();
        fileStorage.saveUsers(users);
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}