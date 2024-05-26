package Models;

import java.io.*;
import java.util.ArrayList;

public class Item implements Serializable {
    private String name;
    private String description;
    private double price;
    private int quantity;

    public Item(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void purchased(int items) {
        this.quantity -= items;
    }

    public void increaseStock(int quantity) {
        this.quantity += quantity;
    }

    // toString method for menu items
    public String toString() {
        return "Menu Item : " + name + "\nPrice : " + price + "\nDescription : " + description;
    }

    // Add menu item to the file
    public static void addMenuItem(Item e, int quantity) {
        try {
            File f = new File("MenuItem.ser");
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

    // print menu items from the file
    public static void getMenuItem() {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream("MenuItem.ser"));
            while (true) {
                Item e = (Item) ois.readObject();
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

    // read all the items from the file
    public static ArrayList<Item> readAllMenuItems() {
        ArrayList<Item> list = new ArrayList<Item>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("MenuItem.ser"));
            while (true) {
                Item a = (Item) ois.readObject();
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

    // delete menu item from the file using name.
    public static void deleteMenuItem(String name) {
        ArrayList<Item> list = readAllMenuItems();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                list.remove(i);
                break;
            }
        }
        File f = new File("MenuItem.ser");
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

    // Take menu items parameters, make an object of them and add it to file
    public static boolean addMenuItem(String name, String description, double price, int quantity) {
        boolean available = false;
        ArrayList<Item> list = readAllMenuItems();
        for (Item i : list) {
            if (i.getName() == name && i.getDescription() == description) {
                i.increaseStock(quantity);
                available = true;
            }
        }
        if (!available) {
            Item a = new Item(name, description, price, quantity);
            list.add(a);
        }

        try {
            File f = new File("MenuItem.ser");
            ObjectOutputStream oos;

            for (Item item : list) {
                if (f.exists()) {
                    oos = new MyObjectOutputStream(new FileOutputStream(f));
                } else {
                    oos = new ObjectOutputStream(new FileOutputStream(f));
                }
                oos.writeObject(item);
                oos.close();
            }
            return true;
        } catch (IOException i) {
            System.out.println("Error in Writing File.");
            return false;
        }
    }
}
