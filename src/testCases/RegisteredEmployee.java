package testCases;

import java.sql.ResultSet;
import util.MySQL;

public class RegisteredEmployee {
    
    public boolean isRegisteredEmployee(String EmployeeEmail) {
        try {
            // SQL query to fetch customer by email
            String query = "SELECT `email` FROM employee WHERE `email` = '" + EmployeeEmail + "'";
            ResultSet resultSet = MySQL.executeSearch(query);

            // Check if the result set has at least one record
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        }
    }
    
}
