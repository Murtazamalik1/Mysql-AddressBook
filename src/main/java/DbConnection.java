import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
}
