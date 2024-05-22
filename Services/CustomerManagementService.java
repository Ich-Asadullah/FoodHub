package Services;


import Models.Customer;

import java.util.ArrayList;

public class CustomerManagementService implements Manageable<Customer> {
    private ArrayList<Customer> customers;

    public CustomerManagementService() {
        customers = new ArrayList<>();
    }

    @Override
    public void add(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void update(Customer customer) {
        for (Customer c : customers) {
            if (c.getId() == customer.getId()) {
                c.setName(customer.getName());
                c.setAddress(customer.getAddress());
                c.setPhoneNumber(customer.getPhoneNumber());
            }
        }
    }

    @Override
    public void delete(int customerId) {
        customers.removeIf(c -> c.getId() == customerId);
    }

    public Customer getCustomerById(int customerId) {
        for (Customer c : customers) {
            if (c.getId() == customerId) {
                return c;
            }
        }
        return null;
    }
}
