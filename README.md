# Instructions

### 1. Clone the repository first

### 2. Add the MySQL database class to your repository

#### Location: `src/util/MySQL.java`

```sh
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class MySQL {
     private static Connection connection;

    public static void createConnection() throws Exception {
        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection();
        }
    }

    public static ResultSet executeSearch(String query) throws Exception {
        createConnection();
        return connection.createStatement().executeQuery(query);
    }

    public static Integer executeIUD(String query) throws Exception {
        createConnection();
        return connection.createStatement().executeUpdate(query);
    }
    
    // New method to handle prepared statements (for inserting images)
    public static Integer executeIUDPrepared(PreparedStatement pstmt) throws Exception {
        createConnection();
        return pstmt.executeUpdate();
    }

    // Method to prepare a statement (for image insertion)
    public static PreparedStatement prepareStatement(String sql) throws Exception {
        createConnection();
        return connection.prepareStatement(sql);
    }

}

```

### 3. Make a copy of nbproject.sample and rename it to nbproject
### 4. Open the project using NetBeans IDE (Step 3 is required for this step to work)
### 5. Copy the Google Drive library collection to your local PC 
```sh 
https://drive.google.com/drive/folders/1O-sHmuYiOkjf923ZMJpEG81Ht-lbuwJ1?usp=sharing
```
### 6. Now, add the library files to the project 
