package models;

public class User {
    private String name;
    private String password;
    private Account account;

    public User(String name, String password, Account account) {
        this.name = name;
        this.password = password;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Account getAccount() {
        return account;
    }
}