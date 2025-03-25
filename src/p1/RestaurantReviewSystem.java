package p1;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class RestaurantReviewSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel panelContainer;
    private Reviewer currentUser;
    private ArrayList<Reviewer> users = new ArrayList<>();
    private String[] restaurantNames = {"McDonald's", "KFC", "Pizza Hut", "Subway", "Starbucks"};

    public RestaurantReviewSystem() {
        frame = new JFrame("Restaurant Review System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        panelContainer = new JPanel(cardLayout);

        panelContainer.add(loginPanel(), "Login");
        panelContainer.add(registerPanel(), "Register");
        panelContainer.add(mainPanel(), "Main");

        frame.add(panelContainer);
        frame.setVisible(true);
    }

    private JPanel loginPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new GridLayout(6, 1));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Fields cannot be empty!");
                return;
            }

            for (Reviewer user : users) {
                if (user.login(username, password)) {
                    currentUser = user;
                    cardLayout.show(panelContainer, "Main");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Invalid credentials!");
        });

        registerButton.addActionListener(e -> cardLayout.show(panelContainer, "Register"));

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        return panel;
    }

    private JPanel registerPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK);
        panel.setLayout(new GridLayout(6, 1));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");

        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Fields cannot be empty!");
                return;
            }

            for (Reviewer user : users) {
                if (user.username.equals(username)) {
                    JOptionPane.showMessageDialog(frame, "Username already exists!");
                    return;
                }
            }

            users.add(new Reviewer(username, password));
            JOptionPane.showMessageDialog(frame, "Registration successful!");
            cardLayout.show(panelContainer, "Login");
        });

        backButton.addActionListener(e -> cardLayout.show(panelContainer, "Login"));

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(registerButton);
        panel.add(backButton);

        return panel;
    }

    private JPanel mainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setLayout(new GridLayout(9, 1));

        JComboBox<String> restaurantDropdown = new JComboBox<>(restaurantNames);
        JComboBox<String> ratingDropdown = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        JTextArea reviewArea = new JTextArea();
        JButton submitReviewButton = new JButton("Submit Review");
        JButton viewReviewsButton = new JButton("View Reviews");
        JButton logoutButton = new JButton("Logout");

        submitReviewButton.addActionListener(e -> {
            if (currentUser != null) {
                String restaurant = (String) restaurantDropdown.getSelectedItem();
                String rating = (String) ratingDropdown.getSelectedItem();
                String comment = reviewArea.getText().trim();

                if (comment.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Comment cannot be empty!");
                    return;
                }

                currentUser.writeReview(restaurant, Integer.parseInt(rating), comment);
                JOptionPane.showMessageDialog(frame, "Review submitted!");
            }
        });

        viewReviewsButton.addActionListener(e -> {
            if (currentUser != null) {
                StringBuilder reviews = new StringBuilder("Your Reviews:\n");
                for (Review review : currentUser.getReviews()) {
                    reviews.append(review.getReviewDetails()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, reviews.toString());
            }
        });

        logoutButton.addActionListener(e -> {
            currentUser = null;
            frame.dispose();
        });

        panel.add(new JLabel("Restaurant Name:"));
        panel.add(restaurantDropdown);
        panel.add(new JLabel("Rating:"));
        panel.add(ratingDropdown);
        panel.add(new JLabel("Comment:"));
        panel.add(reviewArea);
        panel.add(submitReviewButton);
        panel.add(viewReviewsButton);
        panel.add(logoutButton);

        return panel;
    }

    public static void main(String[] args) {
        new RestaurantReviewSystem();
    }
}
