package connection.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class UserConnection implements UserConnectionInterface {
    public java.sql.Connection connection = null;
    public PreparedStatement preparedStatement = null;
    private String url  = "jdbc:mysql://localhost:3366/userdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private String login = "root";
    private String pass = "alex1996";

    public void connect()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url,login,pass);
        }catch (SQLException e)
        {
            System.out.println("Connection Failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }catch (InstantiationException e)
        {

            e.printStackTrace();
        }
    }


    public void closeConnect()
    {
        try {
            connection.close();
        }catch (SQLException e)
        {
            System.out.println("Close connection Failed");
        }
    }

    public void prepareToCreate()
    {
        String insertQueryStatement = "insert into users(user_name,user_age,user_employment,user_hoby) value (?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
        }catch (SQLException e)
        {
            System.out.println("Prepare Statement Failed");
            e.printStackTrace();
        }

    }

    public void prepareToDelete() {
        String inserQueryStatement = "update users" +
                " set user_name = NULL , user_age = NULL , user_employment = NULL , user_hoby = NULL " +
                "where user_id = ?";
        try {
            preparedStatement = connection.prepareStatement(inserQueryStatement);
        }catch (SQLException e)
        {
            System.out.println("Prepare Statement Failed");
            e.printStackTrace();
        }
    }

    public void prepareToUpdate() {
        String insertQueryStatement = "update users" +
                " set user_name = ?, user_age = ?, user_employment = ?, user_hoby = ?" +
                " where user_id = ?";
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
        }catch (SQLException e)
        {
            System.out.println("Prepare Statement Failed");
            e.printStackTrace();
        }
    }

    public void prepareToGet()
    {
        String insertQueryStatement = "SELECT user_name, user_age, user_employment, user_hoby" +
                " FROM users" +
                " where user_id = ?";
        try {
            preparedStatement = connection.prepareStatement(insertQueryStatement);
        }catch (SQLException e)
        {
            System.out.println("Prepare Statement Failed");
            e.printStackTrace();
        }

    }

    public void closePrepare()
    {
        try {
            preparedStatement.close();
        }catch (SQLException e)
        {
            System.out.println("Close prepare Failed");
        }
    }



}