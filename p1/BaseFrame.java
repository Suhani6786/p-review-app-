package p1;
import java.awt.*;
import java.awt.event.*;

abstract class BaseFrame extends Frame {
    public BaseFrame(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
        setLayout(new FlowLayout());
        setBackground(new Color(200, 220, 240)); // Light blue background

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0); // Exit the program
            }
        });

        setVisible(true);
    }
}
