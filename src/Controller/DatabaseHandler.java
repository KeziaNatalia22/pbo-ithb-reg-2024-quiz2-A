package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;
import javax.swing.JOptionPane;

public class DatabaseHandler extends SQLException{

    public Connection con;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/quiz2_pbo_reg_2024_1123001";
    // private String url = "jdbc:mysql://localhost/db_test?serverTimezone=" + TimeZone.getDefault().getID();
    private String username = "root";
    private String password = "";

    private Connection logOn() {
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, "Error Ocurred when login" + ex);
        }
        return con;
    }
    private void logOff() {
        try {
            // tutup koneksi
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error Ocurred when login" + ex);
        }
    }

    public void connect() {
        try {
            con = logOn();
        } catch (Exception ex) {
            System.out.println("Error occured when connecting to database");
        }
    }

    public void disconnect() {
        try {
            logOff();
        } catch (Exception ex) {
            System.out.println("Error occured when connecting to database");
        }
    }
}