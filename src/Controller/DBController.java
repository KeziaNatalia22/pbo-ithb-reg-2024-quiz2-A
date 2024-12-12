package Controller;

import java.io.File;
import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.text.View;

import Model.*;

public class DBController {

    static DatabaseHandler conn = new DatabaseHandler();

    public static int Login(String email, String password){
        Users users = null;
        String query = "select * from users where email = ? and password = ?";
        
        try {
            conn.connect();
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                users = new Users();
                users.setId(rs.getInt("Id_User"));
                JOptionPane.showMessageDialog(null, "Login Berhasil", "Login", JOptionPane.DEFAULT_OPTION);
                return users.getId();
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return 0;
    }

    public static Books selectBook(){
        Books book = null;

        String query = "select * from books";
        
        try {
            conn.connect();
            PreparedStatement stmt = conn.con.prepareStatement(query);

            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                book = new Books();
                book.setId(rs.getInt("book_id"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("tittle"));
                book.setGenre(rs.getString("genre"));
                book.setPrice(rs.getInt("price"));
                return book;
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return null;
    }

    public static int insertTransaction (int bookd_id, int user_id){
        String query = "insert into transaction (user_id, book_id) values (?, ?)";
        
        try {
            conn.connect();
            PreparedStatement stmt = conn.con.prepareStatement(query);
            
            stmt.setInt(1, user_id);
            stmt.setInt(2, bookd_id);
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return 0;
    } 
}

