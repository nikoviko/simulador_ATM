package services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import models.User;
import models.Account;

public class FileStorageService {

    private static final String FILE_PATH = "data/users.txt";

    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(";");
                if (userData.length == 3) {
                    String name = userData[0];
                    String password = userData[1];
                    double balance = Double.parseDouble(userData[2]);
                    users.add(new User(name, password, new Account("0000", balance))); // Número fictício
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void saveUsers(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                bw.write(user.getName() + ";" + user.getPassword() + ";" + user.getAccount().getBalance());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}