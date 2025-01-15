package testCases;

import java.sql.ResultSet;
import util.MySQL;

public class RegisteredCategories {

    public boolean isRegisteredCategories(String category) {
        try {
            // SQL query to fetch customer by email
            String query = "SELECT * FROM category WHERE LOWER(name) = '" + category.toLowerCase() + "'";
            ResultSet resultSet = MySQL.executeSearch(query);

            // Check if the result set has at least one record
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        }
    }
}
