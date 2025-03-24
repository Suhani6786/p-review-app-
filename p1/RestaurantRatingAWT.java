package p1;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class RestaurantRatingAWT extends BaseFrame implements ActionListener {
    Choice restaurantChoice;
    TextArea ratingDisplay;
    Panel ratingPanel;
    Button submitButton;
    int[] ratings;
    int[] count;
    static HashMap<String, User> users = new HashMap<>();
    static String loggedInUser = null;

    public RestaurantRatingAWT() {
        super("Restaurant Rating System", 500, 400);

        if (loggedInUser == null) {
            new LoginFrame();
            dispose();
            return;
        }

        add(new Label("Select Restaurant:"));
        restaurantChoice = new Choice();
        restaurantChoice.add("Restaurant A");
        restaurantChoice.add("Restaurant B");
        restaurantChoice.add("Restaurant C");
        add(restaurantChoice);

        ratingPanel = new Panel(new FlowLayout());
        ratingPanel.add(new Label("Rate (1-5):"));

        CheckboxGroup ratingGroup = new CheckboxGroup();
        for (int i = 1; i <= 5; i++) {
            ratingPanel.add(new Checkbox(String.valueOf(i), ratingGroup, false));
        }
        add(ratingPanel);

        submitButton = new Button("Submit Rating");
        submitButton.setBackground(Color.GREEN);
        submitButton.addActionListener(this);
        add(submitButton);

        ratingDisplay = new TextArea(5, 40);
        add(ratingDisplay);

        ratings = new int[3];
        count = new int[3];
    }

    public void actionPerformed(ActionEvent e) {
        int selectedIndex = restaurantChoice.getSelectedIndex();
        int rating = getSelectedRating();

        if (rating == 0) {
            ratingDisplay.setText("Please select a rating before submitting.");
            return;
        }

        ratings[selectedIndex] += rating;
        count[selectedIndex]++;
        double avgRating = (double) ratings[selectedIndex] / count[selectedIndex];
        ratingDisplay.setText(restaurantChoice.getSelectedItem() + " Average Rating: " + String.format("%.2f", avgRating));
    }

    private int getSelectedRating() {
        for (Component c : ratingPanel.getComponents()) {
            if (c instanceof Checkbox) {
                Checkbox cb = (Checkbox) c;
                if (cb.getState()) {
                    return Integer.parseInt(cb.getLabel());
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        users.put("admin", new User("admin", "password")); // Default user
        new LoginFrame();
    }
}
