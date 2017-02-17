package akatomakhin.apps.usermanager.connection.db.mysql;

import akatomakhin.apps.usermanager.connection.db.UserDAO;
import akatomakhin.apps.usermanager.user.object.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class MySQLUserDAO implements UserDAO {

    /*
    * Create response to create some user
     * and prepare data to send to the server
     */
    public void createUser(User user) {
        PreparedStatement preparedStatement = null;
        Connection connection = connect();
        String insertQueryStatement = "insert into users(user_name,user_age,user_employment,user_hoby) value (?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
            preparedStatement.setString('1', user.getName());
            preparedStatement.setInt('2', user.getAge());
            preparedStatement.setString('3', user.getEmployment());
            preparedStatement.setString('4', user.getHoby());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Prepare Statement to create Failed");
            e.printStackTrace();
        }
        closeConnect(connection);
        closePrepare(preparedStatement);
    }

    /*
    * Create response to delete some user
     * and prepare data to send to the server
     */
    public void deleteUser(int id) {
        PreparedStatement preparedStatement = null;
        java.sql.Connection connection = connect();
        String insertQueryStatement = "delete from users " +
                "where user_id = ?";
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Prepare Statement to delete Failed");
            e.printStackTrace();
        }
        closeConnect(connection);
        closePrepare(preparedStatement);
    }

    /*
    * Create response to update some user
     * and prepare data to send to the server
     */
    public void updateUser (User user) {
        PreparedStatement preparedStatement = null;
        java.sql.Connection connection = connect();
        String insertQueryStatement = "update users" +
                " set user_name = ?, user_age = ?, user_employment = ?, user_hoby = ?" +
                " where user_id = ?";
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getEmployment());
            preparedStatement.setString(4, user.getHoby());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Prepare Statement to update Failed");
            e.printStackTrace();
        }
        closeConnect(connection);
        closePrepare(preparedStatement);
    }

    /*
    * Create request to get user data
     * and prepare data to send to the server
     */
    public User getUser(int id) {
        PreparedStatement preparedStatement = null;
        java.sql.Connection connection = connect();
        String insertQueryStatement = "SELECT user_id, user_name, user_age, user_employment, user_hoby" +
                " FROM users" +
                " where user_id = ?";
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("user_name"));
                user.setAge(resultSet.getInt("user_age"));
                user.setEmployment(resultSet.getString("user_employment"));
                user.setHoby(resultSet.getString("user_hoby"));

            }
        } catch (SQLException e) {
            System.out.println("Prepare Statement to get Failed");
            e.printStackTrace();
        }
        closeConnect(connection);
        closePrepare(preparedStatement);
        return user;
    }

    /* statement for DB to get all users */
    public ArrayList<User> getAllUsers() {
        PreparedStatement preparedStatement = null;
        java.sql.Connection connection = connect();
        ArrayList<User> allUsers = new ArrayList<User>();
        String insertQueryStatement = "SELECT * FROM users WHERE user_id IS NOT NULL";
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next() == true) {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("user_name"));
                user.setAge(resultSet.getInt("user_age"));
                user.setEmployment(resultSet.getString("user_employment"));
                user.setHoby(resultSet.getString("user_hoby"));
                allUsers.add(user);
            }
        }catch (SQLException e){
            System.out.println("Prepare Statement to get all Failed");
            e.printStackTrace();
        }
        closeConnect(connection);
        closePrepare(preparedStatement);
        return allUsers;
    }

    /* Close prepare statement */
    private void closePrepare(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Close prepare Failed");
        }
    }

    /* Here we make connection to the server */
    private Connection connect() {
        /* It`s variables for log to the DB server */
        String url = "jdbc:mysql://localhost:3366/userdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        String login = "root";
        String pass = "alex1996";

        java.sql.Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, login, pass);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {

            e.printStackTrace();
        }
        return connection;
    }

    /* Close connection to the server */
    private void closeConnect(java.sql.Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Close connection Failed");
        }
    }
}
