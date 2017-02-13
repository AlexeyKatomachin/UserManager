package connection.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class UserConnection implements UserConnectionInterface {

    /*
    * It`s a connection variables
     */
    public java.sql.Connection connection = null;
    public PreparedStatement preparedStatement = null;

    /*
    * It`s variables for log to the DB server
     */
    private String url = "jdbc:mysql://localhost:3366/userdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private String login = "root";
    private String pass = "alex1996";

    /*
    * Here we make connection to the server
     */
    public void connect() {
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
    }

    /*
    * Here we close connection to the server
     */
    public void closeConnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Close connection Failed");
        }
    }

    /*
    * Here we create response to create some user
     * and prepare data to send to the server
     */
    public void prepareToCreate() {
        String insertQueryStatement = "insert into users(user_name,user_age,user_employment,user_hoby) value (?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
        } catch (SQLException e) {
            System.out.println("Prepare Statement to create Failed");
            e.printStackTrace();
        }

    }

    /*
    * Here we create response to delete some user
     * and prepare data to send to the server
     */
    public void prepareToDelete() {
        String insertQueryStatement = "delete from users " +
                "where user_id = ?";
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
        } catch (SQLException e) {
            System.out.println("Prepare Statement to delete Failed");
            e.printStackTrace();
        }
    }

    /*
    * Here we create response to update some user
     * and prepare data to send to the server
     */
    public void prepareToUpdate() {
        String insertQueryStatement = "update users" +
                " set user_name = ?, user_age = ?, user_employment = ?, user_hoby = ?" +
                " where user_id = ?";
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
        } catch (SQLException e) {
            System.out.println("Prepare Statement to update Failed");
            e.printStackTrace();
        }
    }

    /*
    * Here we create request to get user data
     * and prepare data to send to the server
     */
    public void prepareToGet() {
        String insertQueryStatement = "SELECT user_id, user_name, user_age, user_employment, user_hoby" +
                " FROM users" +
                " where user_id = ?";
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
        } catch (SQLException e) {
            System.out.println("Prepare Statement to get Failed");
            e.printStackTrace();
        }

    }

    /*
    * Prepare statement for DB to get all users
    */
    public void prepareToGetAllID() {
        String insertQueryStatement = "SELECT user_id FROM users WHERE user_id IS NOT NULL";
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
        }catch (SQLException e){
            System.out.println("Prepare Statement to get all Failed");
            e.printStackTrace();
        }
    }

    /*
    * Prepare statement for DB to get quantity of ID
    */
    public void prepareToGetQuantityID() {
        String insertQueryStatement = "SELECT count(*) AS quantityId FROM users WHERE user_id IS NOT NULL";
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
        }catch (SQLException e){
            System.out.println("Prepare Statement to get all Failed");
            e.printStackTrace();
        }
    }

    /*
    * Close prepare statement
     */
    public void closePrepare() {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Close prepare Failed");
        }
    }


}
