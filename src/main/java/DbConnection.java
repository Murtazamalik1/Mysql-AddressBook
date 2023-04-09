import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
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
    public void updateTable(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Name That You Want To Edit ");
        String name = scanner.next();
        System.out.println("Enter the Name of Book ");
        String bookName = scanner.next();
        String query = String.format("update Person set firstName=? , lastName=?,address=?,city=?,state=?,phoneNumber=?,zip=? where firstName = '%s' and BookType = '%s'", name,bookName);
        System.out.println("Enter New firstName");
        String firstName = scanner.next();
        System.out.println("Enter New lastName");
        String lastName = scanner.next();
        System.out.println("Enter New Address");
        String address = scanner.next();
        System.out.println("Enter New City");
        String city = scanner.next();
        System.out.println("Enter New State");
        String state = scanner.next();
        System.out.println("Enter New Phone Number");
        String phoneNumber = scanner.next();
        System.out.println("Enter New Zip Code");
        String zip = scanner.next();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, state);
            preparedStatement.setInt(6, Integer.parseInt(phoneNumber));
            preparedStatement.setInt(7, Integer.parseInt(zip));
            preparedStatement.executeUpdate();
            System.out.println("Updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteFromTable(Connection connection) {

        String query = "delete from Person where firstName = ? and BookType=? ";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter The FirstName Of Person That You Want To Delete ");
        String name = scanner.next();
        System.out.println("Enter BookType Name ");
        String bookType = scanner.next();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,bookType);
            preparedStatement.executeUpdate();
            System.out.println("Deleted Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void chooseAddressBook(Connection connection){
        String query = "select * from Person where bookType=?";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter TypeBook Name");
        String bookType = scanner.next();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
                System.out.println(resultSet.getString(5));
                System.out.println(resultSet.getInt(6));
                System.out.println(resultSet.getInt(7));
                System.out.println(resultSet.getString(8));
            }
            System.out.println("Printed");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void printCSVData(Connection connection){
        try {

            String filePath = "C:\\Users\\MAHAVEER TECH\\Desktop\\AddressBook.CSV";

            String query = "insert into  Person(firstName,lastName,address,city,state,phoneNumber,zip,BookType) VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(query);
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            String lineText = null;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String firstName = data[1];
                String lastName = data[2];
                String address = data[3];
                String city = data[4];
                String state = data[5];
                String phoneNo = data[6];
                String zip = data[7];
                String bookType = data[8];

                statement.setString(1,firstName );
                statement.setString(2, lastName);
                statement.setString(3, address);
                statement.setString(4, city);
                statement.setString(5, state);
                statement.setInt(6, Integer.parseInt(phoneNo));
                statement.setInt(7, Integer.parseInt(zip));
                statement.setString(8, bookType);

                statement.executeUpdate();
            }
            lineReader.close();
             connection.close();
            System.out.println("data inserted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
