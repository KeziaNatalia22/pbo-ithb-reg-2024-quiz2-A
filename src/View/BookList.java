package View;

import Controller.Controller;
import Controller.DBController;

import java.util.Properties;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.text.DateFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Model.*;

public class BookList {
    JFrame frame;
    JPanel panel;

    public BookList(int idUser) {
        showForm(idUser);
    }

    public void showForm(int idUser) {
        Books book = DBController.selectBook();

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize(); // GET MY SCREEN SIZE

        int screenWidth = screenSize.width; // GET PIXELS FOR WIDTH
        int screenHeight = screenSize.height; // GET PIXELS FOR HEIGHT

        final int FRAME_WIDTH = 1000; // SET WIDTH
        final int FRAME_HEIGHT = 700; // SET WEIGHT

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // SET START LOCATION FOR X
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // SET START LOCATION FOR Y

        frame = new JFrame("Menu Book List"); // CREATE FRAME AND SET TITLE

        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // SET FRAME BOUND
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        JButton transactionButton = new JButton("Transactions");
        transactionButton.setBounds(50, 10, 100, 30);
        panel.add(transactionButton);

        transactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new TransactionsInfo(idUser);
            }
        });

        JLabel labelBuku = new JLabel("Title" + book.getTitle());
        labelBuku.setBounds(50, 50, 100, 30);
        panel.add(labelBuku);

        JLabel author = new JLabel("Author :" + book.getAuthor());
        author.setBounds(50, 90, 100, 30);
        panel.add(author);

        JLabel genre = new JLabel("Genre :" + book.getGenre());
        genre.setBounds(50, 130, 100, 30);
        panel.add(genre);

        JLabel price = new JLabel("Price : " + book.getPrice());
        price.setBounds(50, 170, 100, 30);
        panel.add(price);

        JButton buy = new JButton("Buy Book");
        buy.setBounds(50, 210, 100, 30);
        panel.add(buy);

        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                if(DBController.insertTransaction(book.getId(), idUser) > 0){
                    JOptionPane.showMessageDialog(frame, "Berhasil berhasil dibeli");
                } else {
                    JOptionPane.showMessageDialog(frame, "Gagal Membeli");
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}