import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Models.*;

public class Main extends JFrame {

    User user;

    public Main() {
        // Set the frame properties
        setTitle("Main Page");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add a label
        JLabel loginLabel = new JLabel("Login as");
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(loginLabel, gbc);

        // Add Admin button
        JButton adminButton = new JButton("Admin");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(adminButton, gbc);

        // Add User button
        JButton userButton = new JButton("Customer");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(userButton, gbc);

        // Add action listeners to the buttons
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPage("Admin");
            }
        });

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPage("Customer");
            }
        });

        // Add the panel to the frame
        add(panel);

        // Make the frame visible
        setVisible(true);
    }

    public void loginPage(String type) {
        // Hide the main page
        setVisible(false);

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
        gbc.insets = new Insets(10, 10, 10, 10);
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
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(loginButton, gbc);

        if (type == "Customer") {
            // Don't have an account
            JLabel signupLabel = new JLabel("Don't have an account?");
            loginLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(10, 10, 10, 10);
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
                    signupPage(loginFrame);
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
                setVisible(true);
            }
        });
    }

    public void signupPage(JFrame prevFrame) {
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
                    setVisible(true);
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

    public static void main(String[] args) {
        // Create an instance of the App
        new Main();

        // Admin.addAdmin(new Admin(1,"Asadullah","03074693105", "Owner",
        // "IchAsadullah", "@1234@"));
    }
}
