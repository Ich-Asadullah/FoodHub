import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Models.*;

public class Main {
    private static User user;
    private static JTextArea menuItemsArea;
    private static String menuItemsText = "";
    private static ArrayList<Item> items;
    private static ArrayList<Item> cart = new ArrayList<Item>();

    public static void refreshItems() {
        items = Item.readAllMenuItems();
        menuItemsText = "";
        for (int i = 0; i < items.size(); i++) {
            menuItemsText += (i + 1) + ". " + items.get(i).getName() + " - $" + items.get(i).getPrice() + "\n";
        }
    }

    public static void main(String[] args) {

        // Create the main frame
        JFrame frame = new JFrame("Restaurant Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add a title label
        JLabel titleLabel = new JLabel("Welcome to LEO's Cafe!");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        // Add some spacing
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Add a menu title
        JLabel menuTitle = new JLabel("Our Menu");
        menuTitle.setFont(new Font("Serif", Font.BOLD, 22));
        menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(menuTitle);

        // Create a scroll pane for the menu items
        menuItemsArea = new JTextArea(20, 40);
        menuItemsArea.setFont(new Font("Serif", Font.PLAIN, 16));
        menuItemsArea.setEditable(false);
        refreshItems();
        menuItemsArea.setText(menuItemsText);

        JScrollPane scrollPane = new JScrollPane(menuItemsArea);
        panel.add(scrollPane);

        // Add some spacing
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Admin Login Button
        JButton adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPage("Admin", frame);
            }
        });
        panel.add(adminLoginButton);

        // Customer Login Button
        JButton customerLoginButton = new JButton("Customer Login");
        customerLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPage("Customer", frame);
            }
        });
        panel.add(customerLoginButton);

        // Add the panel to the frame
        frame.add(panel);

        // Set the frame to be visible
        frame.setVisible(true);
    }

    public static void loginPage(String type, JFrame mainFrame) {
        // Hide the main page
        mainFrame.setVisible(false);

        // Create a new frame for the login page
        JFrame loginFrame = new JFrame(type + " Login Page");
        loginFrame.setSize(400, 300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null); // Center the frame

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add a label for the title
        JLabel loginLabel = new JLabel(type + " Login");
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(loginLabel, gbc);

        // Add a label and text field for the username
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(usernameField, gbc);

        // Add a label and password field for the password
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(passwordField, gbc);

        // Add a login button
        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        if (type == "Customer") {
            // Don't have an account
            JLabel signupLabel = new JLabel("Don't have an account?");
            loginLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            panel.add(signupLabel, gbc);
            // Add a signup button
            JButton signupButton = new JButton("Signup");
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(10, 10, 10, 10);
            panel.add(signupButton, gbc);

            signupButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    signupPage(loginFrame, mainFrame);
                }
            });

            // Login Button Event Listner
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    user = Customer.signIn(username, password);
                    if (user == null) {
                        JOptionPane.showMessageDialog(null, "Wrong Username or Password");
                    } else {
                        JOptionPane.showMessageDialog(null, "Signed in as: " + username);
                        showCustomerControls(loginFrame, mainFrame);
                    }
                }
            });
        } else {
            // Login Button Event Listner
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    user = Admin.signIn(username, password);
                    if (user == null) {
                        JOptionPane.showMessageDialog(null, "Wrong Username or Password");
                    } else {
                        JOptionPane.showMessageDialog(null, "Signed in as: " + username);
                        showAdminControls(loginFrame, mainFrame);
                    }
                }
            });
        }

        // Add the panel to the login frame
        loginFrame.add(panel);

        // Make the login frame visible
        loginFrame.setVisible(true);

        // Add a signup button
        JButton backMain = new JButton("Back");
        backMain.setPreferredSize(new Dimension(75, 20));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        panel.add(backMain, gbc);

        backMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(false);
                mainFrame.setVisible(true);
            }
        });
    }

    public static void signupPage(JFrame prevFrame, JFrame mainFrame) {
        // Create a new frame for the signup page
        prevFrame.dispose();
        JFrame signupFrame = new JFrame("Customer Signup Page");
        signupFrame.setSize(400, 400);
        signupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signupFrame.setLocationRelativeTo(null); // Center the frame

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add a label for the title
        JLabel signupLabel = new JLabel("Signup");
        signupLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(signupLabel, gbc);

        // Add a label and text field for the name
        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(nameField, gbc);

        // Add a label and text field for the phone number
        JLabel phoneLabel = new JLabel("Phone Number:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(phoneLabel, gbc);

        JTextField phoneField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(phoneField, gbc);

        // Add a label and text field for the address
        JLabel addressLabel = new JLabel("Address:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(addressLabel, gbc);

        JTextField addressField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(addressField, gbc);

        // Add a label and text field for the username
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(usernameField, gbc);

        // Add a label and password field for the password
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(passwordField, gbc);

        // Add a signup button
        JButton signupButton = new JButton("Signup");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(signupButton, gbc);

        // Add action listener to the signup button
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Create a new customer
                user = Customer.signUP(name, phone, address, username, password);

                if (user == null) {
                    JOptionPane.showMessageDialog(null, "Username Already Taken");
                }

                else {
                    // Display a confirmation message
                    JOptionPane.showMessageDialog(signupFrame, "New customer created:\n" +
                            "ID: " + user.getId() + "\n" +
                            "Username: " + username + "\n" +
                            "Name: " + name + "\n" +
                            "Phone: " + phone + "\n" +
                            "Address: " + address + "\n");

                    // Close the signup frame
                    signupFrame.dispose();

                    // Show the main page
                    mainFrame.setVisible(true);
                }
            }
        });

        // Add a signup button
        JButton backMain = new JButton("Back");
        backMain.setPreferredSize(new Dimension(75, 20));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(backMain, gbc);

        backMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signupFrame.dispose();
                prevFrame.setVisible(true);
            }
        });

        // Add the panel to the signup frame
        signupFrame.add(panel);

        // Make the signup frame visible
        signupFrame.setVisible(true);

    }

    private static void showAdminControls(JFrame prevFrame, JFrame parentFrame) {
        prevFrame.setVisible(false);
        JFrame adminFrame = new JFrame("Admin Controls");
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel adminLabel = new JLabel("Admin Controls");
        adminLabel.setFont(new Font("Serif", Font.BOLD, 22));
        adminLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(adminLabel);

        // Add some spacing
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Add Item Button
        JButton addItemButton = new JButton("Add Menu Item");
        addItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addItemButton.addActionListener(e -> addMenuItem());
        panel.add(addItemButton);

        // Remove Item Button
        JButton removeItemButton = new JButton("Remove Menu Item");
        removeItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeItemButton.addActionListener(e -> removeMenuItem(parentFrame));
        panel.add(removeItemButton);

        // Logout Button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.addActionListener(e -> {
            adminFrame.dispose();
            parentFrame.setVisible(true);
        });
        panel.add(logoutButton);

        adminFrame.add(panel);
        adminFrame.setVisible(true);
        parentFrame.setVisible(false); // Hide the parent frame
    }

    private static void addMenuItem() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JLabel itemLabel = new JLabel("Item name:");
        JTextField itemField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        panel.add(itemLabel);
        panel.add(itemField);
        panel.add(priceLabel);
        panel.add(priceField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Menu Item", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String newItem = itemField.getText() + " - $" + priceField.getText();
            menuItemsText += "\n" + newItem;
            menuItemsArea.setText(menuItemsText);
            Item.addMenuItem(new Item(itemField.getText(), Double.parseDouble(priceField.getText())));
        }
    }

    private static void removeMenuItem(JFrame mainFrame) {
        mainFrame.setVisible(true);
        String itemToRemove = JOptionPane.showInputDialog(null, "Enter the item name to remove:",
                "Remove Menu Item", JOptionPane.PLAIN_MESSAGE);
        Item itemRemove = searchFromArray(items, itemToRemove);
        if (itemToRemove != null && !itemToRemove.trim().isEmpty()) {
            menuItemsText = menuItemsText.replace(itemToRemove + "\n", "").replace("\n" + itemToRemove, "");
            menuItemsArea.setText(menuItemsText);
            items.remove(itemRemove);
            Item.deleteMenuItem(itemToRemove);
        }
    }

    private static void showCustomerControls(JFrame prevFrame, JFrame parentFrame) {
        prevFrame.setVisible(false);
        JFrame customerFrame = new JFrame("Customer Controls");
        customerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerFrame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel customerLabel = new JLabel("Customer Controls");
        customerLabel.setFont(new Font("Serif", Font.BOLD, 22));
        customerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(customerLabel);

        // Add some spacing
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Add to Cart Button
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.addActionListener(e -> addToCart(parentFrame));
        panel.add(addToCartButton);

        // View Cart Button
        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewCartButton.addActionListener(e -> viewCart());
        panel.add(viewCartButton);

        // Add some spacing
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Logout Button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.addActionListener(e -> {
            customerFrame.dispose();
            parentFrame.setVisible(true);
        });
        panel.add(logoutButton);

        customerFrame.add(panel);
        customerFrame.setVisible(true);
        parentFrame.setVisible(false); // Hide the parent frame
    }

    private static Item searchFromArray(ArrayList<Item> items, String item) {
        for (Item i : items) {
            if (i.getName().equals(item)) {
                return i;
            }
        }
        return null;
    }

    private static void addToCart(JFrame mainFrame) {
        items = Item.readAllMenuItems();
        mainFrame.setVisible(true);
        String itemToAdd = JOptionPane.showInputDialog(null, "Enter the Item name to add to cart:",
                "Add to Cart", JOptionPane.PLAIN_MESSAGE);
        Item item = searchFromArray(items, itemToAdd);
        if (item != null) {
            cart.add(item);
            JOptionPane.showMessageDialog(null, "Item added to cart.");
        } else {
            JOptionPane.showMessageDialog(null, "Item not found in the menu.");
        }
    }

    private static double calculateTotal(ArrayList<Item> items) {
        double total = 0;

        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    private static void viewCart() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Your cart is empty.");
        } else {
            double totalPrice = calculateTotal(cart); // Calculate total price
            StringBuilder cartContents = new StringBuilder("Cart Items:\n");
            for (Item item : cart) {
                cartContents.append(item.getName()).append(" - $").append(item.getPrice()).append("\n");
            }

            // Create a panel for cart items and checkout button
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JTextArea cartItemsArea = new JTextArea(cartContents.toString());
            cartItemsArea.setEditable(false); // Make the text area read-only
            JScrollPane scrollPane = new JScrollPane(cartItemsArea); // Add scroll functionality

            JLabel totalPriceLabel = new JLabel("Total Price: $" + totalPrice);
            JButton checkoutButton = new JButton("Checkout");
            checkoutButton.addActionListener(e -> {
                cart.clear(); // Clear cart items
                JOptionPane.getRootFrame().dispose(); // Close the JOptionPane dialog
                JOptionPane.showMessageDialog(null, "Your cart has been checked out. Total Price: $" + totalPrice,
                        "Checkout", JOptionPane.INFORMATION_MESSAGE);
            });

            panel.add(scrollPane); // Add the scrollable cart items area
            panel.add(totalPriceLabel);
            panel.add(checkoutButton);

            // Show the panel in JOptionPane
            JOptionPane.showMessageDialog(null, panel, "Your Cart", JOptionPane.PLAIN_MESSAGE);
        }
    }

}
