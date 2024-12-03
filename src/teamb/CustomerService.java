package teamb;

/*
 * customer service method to retrieve customer information
 * author: LumpkinR
 */

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 public class CustomerService {
    
    public static int getCustomerId(String name, String email, String phone) throws SQLException {
        int customerId = -1; // default value if not found

        try (Connection connection = Connecting.letConnect()) {

            String checkCustomerQuery = "SELECT customer_id FROM Customer WHERE email = ? AND phone = ?";

            try (PreparedStatement checkStmt = connection.prepareStatement(checkCustomerQuery)) {
                checkStmt.setString(1, email);
                checkStmt.setString(2, phone);

                ResultSet resultSet = checkStmt.executeQuery();

                if (resultSet.next()) {
                    customerId = resultSet.getInt("customer_id");
                } else {
                    // customer not found, create a new customer
                    String insertCustomerQuery = """
                                                 INSERT INTO Customer (name, email, phone)
                                                    VALUES (?, ?, ?)
                                                 """;
                             
                             
                    try (PreparedStatement insertStmt = connection.prepareStatement(insertCustomerQuery, Statement.RETURN_GENERATED_KEYS)) {
                        insertStmt.setString(1, name); 
                        insertStmt.setString(2, email);
                        insertStmt.setString(3, phone);
                        insertStmt.executeUpdate();

                        try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                customerId = generatedKeys.getInt(1); // retrieve generated key
                            } else {
                                throw new SQLException("Failed to retrieve generated key.");
                            }
                        }
                    }
                }
            }
        }

        return customerId;
    }
 }