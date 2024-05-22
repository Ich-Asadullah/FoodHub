package Models;



import java.util.ArrayList;

public class Restaurant {
    private int id;
    private String name;
    private String cuisine;
    private double rating;
    private ArrayList<MenuItem> menu;

    public Restaurant(int id, String name, String cuisine, double rating) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.rating = rating;
        this.menu = new ArrayList<>();
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

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public void displayInfo() {
        System.out.println("Restaurant ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Cuisine: " + cuisine);
        System.out.println("Rating: " + rating);
        System.out.println("Menu Items:");
        for (MenuItem item : menu) {
            System.out.println(" - " + item.getName() + ": $" + item.getPrice());
        }
    }
}

