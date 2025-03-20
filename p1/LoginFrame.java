package p1;
import java.awt.*;
import java.awt.event.*;

class LoginFrame extends BaseFrame implements ActionListener {
    TextField usernameField, passwordField;
    Button loginButton, registerButton, submitButton;

    public LoginFrame() {
        super("Login", 350, 250);

        add(new Label("Username:"));
        usernameField = new TextField(20);
        add(usernameField);

        add(new Label("Password:"));
        passwordField = new TextField(20);
        passwordField.setEchoChar('*');
        add(passwordField);

        loginButton = new Button("Login");
        loginButton.setBackground(Color.CYAN);
        loginButton.addActionListener(this);
        add(loginButton);

        registerButton = new Button("Register");
        registerButton.setBackground(Color.ORANGE);
        registerButton.addActionListener(this);
        add(registerButton);
    }

    public void actionPerformed(ActionEvent e) {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (e.getSource() == loginButton) {
            if (RestaurantRatingAWT.users.containsKey(user) && RestaurantRatingAWT.users.get(user).checkPassword(pass)) {
                RestaurantRatingAWT.loggedInUser = user;
                new RestaurantRatingAWT();
            } else {
                System.out.println("Invalid login!");
            }
        } else if (e.getSource() == registerButton) {
            new RegisterFrame();
        }
    }
}
