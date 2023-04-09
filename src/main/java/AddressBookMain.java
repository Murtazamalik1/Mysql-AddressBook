import java.sql.Connection;
import java.sql.DriverManager;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("welcome to addressBook");
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String dataBaseURL = "jdbc:mysql://localhost:3306/AddressBook_System";
            String userName = "root";
            String password = "malik@123";
            Class.forName(driver);
            System.out.println("driver is loaded");
            Connection connection = DriverManager.getConnection(dataBaseURL, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
