package p1;
import java.awt.*;
import java.awt.event.*;

class RegisterFrame extends BaseFrame implements ActionListener {
    TextField usernameField, passwordField;
    Button registerButton;

    public RegisterFrame() {
        super("Register", 350, 250);

        add(new Label("New Username:"));
        usernameField = new TextField(20);
        add(usernameField);

        add(new Label("New Password:"));
        passwordField = new TextField(20);
        passwordField.setEchoChar('*');
        add(passwordField);

        registerButton = new Button("Register");
        registerButton.setBackground(Color.PINK);
        registerButton.addActionListener(this);
        add(registerButton);
    }

    public void actionPerformed(ActionEvent e) {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (!user.isEmpty() && !pass.isEmpty() && !RestaurantRatingAWT.users.containsKey(user)) {
            RestaurantRatingAWT.users.put(user, new User(user, pass));
            System.out.println("User registered successfully!");
            new LoginFrame();
        } else {
            System.out.println("Invalid or existing user!");
        }
    }
}
