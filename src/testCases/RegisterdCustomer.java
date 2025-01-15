package testCases;

import java.sql.ResultSet;
import util.MySQL;

public class RegisterdCustomer {

    public boolean isRegisteredCustomer(String customerEmail) {
        try {
            // SQL query to fetch customer by email
            String query = "SELECT `email` FROM customer WHERE `email` = '" + customerEmail + "'";
            ResultSet resultSet = MySQL.executeSearch(query);

            // Check if the result set has at least one record
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        }
    }
}
