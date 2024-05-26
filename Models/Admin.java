package Models;

import java.io.*;
import java.util.ArrayList;

public class Admin extends User {
    private String designation;

    public Admin(int id, String name, String phoneNumber, String designation, String userName, String password) {
        super(id, name, phoneNumber, userName, password);
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + "\nName: " + getName() + "\nPhone Number: " + getPhoneNumber()
                + "\nDesignation: " + designation;
    }

    // Function to add a new Admin
    public static void addAdmin(Admin e) {
        try {
            File f = new File("admin.ser");
            ObjectOutputStream oos;
            if (f.exists()) {
                oos = new MyObjectOutputStream(new FileOutputStream(f, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }
            oos.writeObject(e);
            oos.close();
        } catch (IOException i) {
            System.out.println("Error in Writing File.");
        }
    }

    // Function to Print All Admin details
    public static void getAdmin() {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream("admin.ser"));
            while (true) {
                Admin e = (Admin) ois.readObject();
                System.out.println(e);
            }
        } catch (ClassNotFoundException e1) {
            System.out.println("ClassNotFoundException. ");
        } catch (EOFException e2) {
            return;
        } catch (IOException e3) {
            System.out.println("File not found in reader. ");
        }
    }

    // Function to Get All Admin details
    public static ArrayList<Admin> readAllAdmins() {
        ArrayList<Admin> list = new ArrayList<Admin>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("admin.ser"));
            while (true) {
                Admin a = (Admin) ois.readObject();
                list.add(a);
            }
        } catch (ClassNotFoundException e1) {
            System.out.println("Class not Found. ");
        } catch (EOFException e2) {
            return list;
        } catch (IOException e3) {
            System.out.println("File not found in reader. ");
        }
        return list;
    }

    // Function to delete an Admin
    public void deleteAdmin(String userName) {
        ArrayList<Admin> list = readAllAdmins();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equals(userName)) {
                list.remove(i);
                break;
            }
        }
        File f = new File("admin.ser");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            for (int i = 0; i < list.size(); i++) {
                oos.writeObject(list.get(i));
            }
        } catch (FileNotFoundException e1) {
            System.out.println("File not found.");
        } catch (IOException e2) {
            System.out.println("Input output exception.");
        }
    }

    // Return last ID
    public static int return_last_id() {
        ArrayList<Admin> list = readAllAdmins();

        Admin a = list.get(list.size() - 1);
        return a.getId();
    }

    // Signup Function for Admin
    public static Admin signUP(String name, String phoneNumber, String designation, String userName,
            String password) {
        ArrayList<Admin> list = readAllAdmins();

        if (!(uniqueUserName(userName, list))) {
            System.out.println("Username Must be Unique");
            return null;
        }
        // Getting last Admin id to allocate a new one
        int id = return_last_id();
        Admin admin = new Admin(id + 1, name, phoneNumber, designation, userName, password);
        addAdmin(admin);
        return admin;
    }

    // Function to signIn as an Admin
    public static Admin signIn(String userName, String password) {
        ArrayList<Admin> list = readAllAdmins();

        for (Admin admin : list) {
            if (admin.validate(userName, password)) {
                return admin;
            }
        }
        return null;
    }
}
