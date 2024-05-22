package Models;


import java.util.ArrayList;

public class Order {
    private int id;
    private Customer customer;
    private Restaurant restaurant;
    private ArrayList<MenuItem> items;
    private double total;

    public Order(int id, Customer customer, Restaurant restaurant, ArrayList<MenuItem> items) {
        this.id = id;
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = items;
        calculateTotal();
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    private void calculateTotal() {
        total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
    }

    public void displayOrder() {
        System.out.println("Order ID: " + id);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Restaurant: " + restaurant.getName());
        System.out.println("Items:");
        for (MenuItem item : items) {
            System.out.println(" - " + item.getName() + ": $" + item.getPrice());
        }
        System.out.println("Total: $" + total);
    }
}
