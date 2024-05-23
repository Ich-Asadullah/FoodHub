package Models;

import java.util.ArrayList;

public class Cart {
    private ArrayList<MenuItem> items;
    private double total;

    public Cart(ArrayList<MenuItem> items) {
        this.items = items;
        this.total = calculateTotal();
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public double calculateTotal() {
        total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void updateCart(MenuItem item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            items.add(item);
        }
    }

}
