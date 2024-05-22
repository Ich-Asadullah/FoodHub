
import Models.*;
import Services.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        CustomerManagementService customerService = new CustomerManagementService();
        RestaurantManagementService restaurantService = new RestaurantManagementService();
        OrderManagementService orderService = new OrderManagementService();

        
        Customer customer1 = new Customer(1, "John Doe", "1234567890", "123 Main St");
        customerService.add(customer1);

        
        Restaurant restaurant1 = new Restaurant(1, "Pizza Palace", "Italian", 4.5);
        MenuItem item1 = new MenuItem("Margherita Pizza", "Classic cheese pizza", 10.0);
        restaurant1.addMenuItem(item1);
        restaurantService.add(restaurant1);

        
        ArrayList<MenuItem> orderItems = new ArrayList<>();
        orderItems.add(item1);
        Order order1 = new Order(1, customer1, restaurant1, orderItems);
        orderService.placeOrder(order1);

        
        order1.displayOrder();
    }
}


