package Models;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
    private int id;
    private String name;
    private String phoneNumber;
    private String userName;
    private String password;

    public User(int id, String name, String phoneNumber, String userName, String password) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public abstract String toString();

    // Validated the login of a user
    public boolean validate(String userName, String password) {
        if (this.userName == userName && this.password == password) {
            return true;
        }
        return false;
    }

    // Checks the uniqueness of a username before signup
    public static boolean uniqueUserName(String userName, ArrayList<? extends User> list) {
        for (User i : list) {
            if (i.getUserName().equals(userName)) {
                return false;
            }
        }
        return true;
    }
}
