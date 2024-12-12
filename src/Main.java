import View.BookList;
import View.Login;
import Controller.DatabaseHandler;

public class Main {
    public static void main(String[] args) {
        new Login();
        new BookList(0);
    }
}