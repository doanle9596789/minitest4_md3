package minitestmd3.service;

import minitestmd3.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserSeviceIpm implements UserSevice{
    private static final String SELECT_ALL_USERS="select u.id,u.name,pr.name from province pr join user u on pr.id = u.province_id";
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/minitest4";
    private static final String jdbcUsername = "root";
    private static  final String jdbcPassword = "10061995";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("connection successfully");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("not found");
        }return connection;
    }


    @Override
    public void insertUser(User user) throws SQLException {

    }

    @Override
    public User selectUser(int id) {
        return null;
    }

    @Override
    public List<User> selectAllUsers() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<User> users = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("u.name");
                String address = rs.getString("pr.name");
                users.add(new User(id, name,address));
            }
        } catch (SQLException e) {
            System.out.println();
        }
        return users;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return false;
    }

    public static void main(String[] args) {

        getConnection();
    }
}
