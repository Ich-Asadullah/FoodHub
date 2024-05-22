package Models;

public class Customer extends User {
    private String address;

    public Customer(int id, String name, String phoneNumber, String address) {
        super(id, name, phoneNumber);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void displayInfo() {
        System.out.println("Customer ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Phone Number: " + getPhoneNumber());
        System.out.println("Address: " + address);
    }
}

