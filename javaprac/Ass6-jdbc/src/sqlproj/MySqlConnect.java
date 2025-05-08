package sqlproj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySqlConnect {
    public static void main(String[] args) {
        try {
            // Load the driver (optional with modern JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/rohit", "root", "123456");
            System.out.println("Connection created");
            
            // Create a statement
            Statement stmt = con.createStatement();

            // Execute query (example: fetching all records)
            stmt.executeQuery("SELECT * FROM customer");

            // Close the connection
            con.close();

 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

