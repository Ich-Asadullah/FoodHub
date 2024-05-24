package Models;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Item> items;
    private double total;

    public Cart() {
    }

    public Cart(ArrayList<Item> items) {
        this.items = items;
        this.total = calculateTotal();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public double calculateTotal() {
        total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void updateCart(Item item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            items.add(item);
        }
    }

}
