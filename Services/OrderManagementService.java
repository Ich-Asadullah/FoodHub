package Services;


import Models.Order;

import java.util.ArrayList;

public class OrderManagementService {
    private ArrayList<Order> orders;

    public OrderManagementService() {
        orders = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public ArrayList<Order> getOrdersByCustomerId(int customerId) {
        ArrayList<Order> customerOrders = new ArrayList<>();
        for (Order o : orders) {
            if (o.getCustomer().getId() == customerId) {
                customerOrders.add(o);
            }
        }
        return customerOrders;
    }
}
