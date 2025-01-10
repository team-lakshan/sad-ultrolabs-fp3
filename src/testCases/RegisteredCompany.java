package testCases;

import java.sql.ResultSet;
import util.MySQL;

public class RegisteredCompany {
    
    public boolean isRegisteredCompany(String CompanyNumber) {
        try {
            // SQL query to fetch customer by email
            String query = "SELECT `hotline_num` FROM company WHERE `hotline_num` = '" + CompanyNumber + "'";
            ResultSet resultSet = MySQL.executeSearch(query);

            // Check if the result set has at least one record
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        }
    }
    
}
