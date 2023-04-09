import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

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
            Scanner scanner = new Scanner(System.in);
            DbConnection call = new DbConnection();
            boolean exit = false;
            while (!exit) {
                System.out.println("What do you Want To Perform");
                System.out.println("1 Create Table 2 InsertInto Table 3 Update Table ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        call.createTable(connection);
                        break;
                    case 2:
                        call.insertTable(connection);
                        break;
                    case 3:
                        call.updateTable(connection);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
