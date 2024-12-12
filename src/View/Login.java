package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.*;


public class Login {
    JFrame frame;
    JPanel panel;

    public Login() {
        Login();
    }

    public void Login() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize(); // GET MY SCREEN SIZE

        int screenWidth = screenSize.width; // GET PIXELS FOR WIDTH
        int screenHeight = screenSize.height; // GET PIXELS FOR HEIGHT

        final int FRAME_WIDTH = 300; // SET WIDTH
        final int FRAME_HEIGHT = 400; // SET WEIGHT

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // SET START LOCATION FOR X
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // SET START LOCATION FOR Y

        frame = new JFrame("Login"); // CREATE FRAME AND SET TITLE
        
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // SET FRAME BOUND
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        JLabel label1 = new JLabel("Email");
        label1.setBounds(30, 50, 150, 30);
        panel.add(label1);

        JTextField textField1 = new JTextField();
        textField1.setBounds(30, 80, 150, 30);
        panel.add(textField1);

        JLabel label2 = new JLabel("Password");
        label2.setBounds(30, 120, 150, 30);
        panel.add(label2);

        JPasswordField passwordField1 = new JPasswordField();
        passwordField1.setBounds(30, 150, 150, 30);
        panel.add(passwordField1);
        String password = new String(passwordField1.getPassword());

        JButton submit = new JButton("Login");
        submit.setBounds(30, 200, 200, 50);
        panel.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                int Id_User = DBController.Login(textField1.getText(), password);

                if (Id_User != 0) {
                    new BookList(DBController.Login(textField1.getText(), password));
                } else {
                    JOptionPane.showMessageDialog(frame, "Login gagal. Periksa email/password Anda.");
                }
            }
        });
        

        frame.setVisible(true);
        frame.add(panel);
    }
}