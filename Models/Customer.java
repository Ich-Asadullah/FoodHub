package Models;

import java.io.*;
import java.util.ArrayList;

public class Customer extends User {
    private String address;

    public Customer(int id, String name, String phoneNumber, String address, String userName, String password) {
        super(id, name, phoneNumber, userName, password);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + "\nName: " + getName() + "\nPhone Number: " + getPhoneNumber()
                + "\nAddress: " + address;
    }

    // Function to add Customer
    public static void addCustomer(Customer e) {
        try {
            File f = new File("customer.ser");
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

    // Function to print all customers
    public static void getCustomer() {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream("customer.ser"));
            while (true) {
                Customer e = (Customer) ois.readObject();
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

    // Function to get all customers
    public static ArrayList<Customer> readAllCustomers() {
        ArrayList<Customer> list = new ArrayList<Customer>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("customer.ser"));
            while (true) {
                Customer a = (Customer) ois.readObject();
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

    // Function to remove a customer
    public void deleteCustomer(String name) {
        ArrayList<Customer> list = readAllCustomers();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                list.remove(i);
                break;
            }
        }
        File f = new File("customer.ser");
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

    // Function to return Last ID
    public static int return_last_id() {
        ArrayList<Customer> list = readAllCustomers();

        Customer a = list.get(list.size() - 1);
        return a.getId();
    }

    // Signup Function for Customer
    public static Customer signUP(String name, String phoneNumber, String address, String userName,
            String password) {
        ArrayList<Customer> list = readAllCustomers();

        if (!(uniqueUserName(userName, list))) {
            System.out.println("Username Must be Unique");
            return null;
        }
        // Getting last Customer id to allocate a new one
        int id = return_last_id();
        Customer customer = new Customer(id + 1, name, phoneNumber, address, userName, password);
        addCustomer(customer);
        return customer;
    }

    // Function to signIn as a Customer
    public static Customer signIn(String userName, String password) {
        ArrayList<Customer> list = readAllCustomers();

        for (Customer customer : list) {
            if (customer.validate(userName, password)) {
                return customer;
            }
        }
        return null;
    }
}
