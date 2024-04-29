import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;
public class AcessDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/crime_control"; //"jdbc:mysql://192.168.0.102:3306/crime_control?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "Sammyrun";
    private static final String PASSWORD = "Sammyrun";

    public static boolean checkLoginCredentials(String username, String password) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM user WHERE Name = ? AND Password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next(); // Return true if there is at least one matching user
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of any database error
        }
    }
    public static Vector<String> getColumnNames(String tableName) {
        Vector<String> columnNames = new Vector<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet rs = metaData.getColumns(null, null, tableName, null)) {
                while (rs.next()) {
                    columnNames.add(rs.getString("COLUMN_NAME"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnNames;
    }

    public static Vector<Vector<Object>> fetchTableData(String tableName) {
        Vector<Vector<Object>> data = new Vector<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM " + tableName;
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                Vector<String> columnNames = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    columnNames.add(metaData.getColumnName(i));
                }

                while (resultSet.next()) {
                    Vector<Object> row = new Vector<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.add(resultSet.getObject(i));
                    }
                    data.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
    public static void addCrime(String description, String location, int officerID, String witnessName,
            String criminalName,String criminal_address,String witness_address,int witness_age,int criminal_age) {
       try(Connection connection=DriverManager.getConnection(URL,USERNAME,PASSWORD)) {
        
        
        // Add criminal to person table with role "Criminal"
        addPerson(connection, criminalName, "Criminal",criminal_address,criminal_age);

        // Add witness to person table with role "Witness"
        addPerson(connection, witnessName, "Witness",witness_address,witness_age);
        addDescription(connection,description,location,officerID);
        updateCasefileWithMaxCriminalId();
       } 
        
        catch (Exception e) {
        System.out.println(e);
       }
    }
   
    private static void addDescription(Connection connection, String description, String location,int officerId)throws SQLException {
        String sql="INSERT INTO crime (Description,Location,Officer_ID) VALUES (?, ?, ?)";
        try(PreparedStatement statement= connection.prepareStatement(sql)){
            statement.setString(1, description);
            statement.setString(2, location);
            statement.setInt(3, officerId);
            statement.executeUpdate();
        }
    }

    public static void updateCasefileWithMaxCriminalId() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT MAX(Person_ID) AS max_id FROM person WHERE Role_Type = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "Suspect");
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int maxId = resultSet.getInt("max_id");
                        updateCasefileCriminalId(connection, maxId);
                    } else {
                        System.out.println("No criminal found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateCasefileCriminalId(Connection connection, int maxId) throws SQLException {
        String updateSql = "UPDATE casefile SET criminal_id = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
            updateStatement.setInt(1, maxId);
            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println(rowsUpdated + " rows updated in casefile.");
        }
    }
    private static void addPerson(Connection connection, String name, String roleType, String address, int age) {
        String sql = "INSERT INTO person (Name, Age, Address, Role_Type) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, address);
            statement.setString(4, roleType);
            statement.executeUpdate();
        } catch (SQLException e) {
            // Log the SQL exception
            e.printStackTrace();
        } finally {
            // Close the PreparedStatement in a finally block to ensure it's always closed
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // Log the exception if closing the statement fails
                    e.printStackTrace();
                }
            }
        }
    }
    public static void addWitnessToCrime(int witness_id, int crime_id)throws SQLException {
        try(Connection connection=DriverManager.getConnection(URL, USERNAME, PASSWORD))
        {
             String sql="UPDATE crime SET Witness_ID = ? WHERE Crime_ID = ?";
             try(PreparedStatement statement=connection.prepareStatement(sql)){
                statement.setInt(1, witness_id);
                statement.setInt(2,crime_id);
                int rowsUpdated = statement.executeUpdate();
                String message;
                if (rowsUpdated > 0) {
                   message="Witness ID updated successfully for crime ID " + crime_id;
                } else {
                    message="No crime found with ID " + crime_id;
                }
                JOptionPane.showMessageDialog(null, message, "Update Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
             }
      
