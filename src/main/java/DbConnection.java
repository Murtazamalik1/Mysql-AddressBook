import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DbConnection {
    public void createTable(Connection connection) {
        String query = "create Table Person( ID int not null auto_increment, firstName varchar(255) not null,lastName varchar(100) not null, address varchar(400) not null,city varchar(100) not null, state varchar(100) not null, phoneNumber BIGINT(13) not null, zip int(6) not null, bookType varchar(100) not null, PRIMARY KEY (Id))";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table  Is Created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertTable(Connection connection) {
        String q = "insert into Person(firstName,lastName,address,city,state,phoneNumber,zip,BookType) values (?,?,?,?,?,?,?,?)";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name");
        String firstName = scanner.next();
        System.out.println("Enter lastName");
        String lastName = scanner.next();
        System.out.println("Enter Address");
        String address = scanner.next();
        System.out.println("Enter City :");
        String city = scanner.next();
        System.out.println("Enter State :");
        String state = scanner.next();
        System.out.println("Enter Phone Number");
        String phoneNumber = scanner.next();
        System.out.println("Enter Zip Code");
        String zip = scanner.next();
        System.out.println("Enter Book Type :");
        String book = scanner.next();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(q);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, state);
            preparedStatement.setInt(6, Integer.parseInt(phoneNumber));
            preparedStatement.setInt(7, Integer.parseInt(zip));
            preparedStatement.setString(8, book);
            preparedStatement.executeUpdate();
            System.out.println("Inserted ..");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
