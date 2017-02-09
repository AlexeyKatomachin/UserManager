package user;

import connection.db.UserConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class UserManager extends UserConnection implements UserInterface{

    public void createUser(String name, String age, String employment, String hoby)  {
        connect();
        prepareToCreate();
        try {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, age);
            preparedStatement.setString(3, employment);
            preparedStatement.setString(4, hoby);
            preparedStatement.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println("Send Failed");
            e.printStackTrace();
        }
        closePrepare();
        closeConnect();
    }

    public void deleteUser(int id) {
        connect();
        prepareToDelete();
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println("Send Failed");
            e.printStackTrace();
        }
        closePrepare();
        closeConnect();
    }

    public void updateUser(String name, String age, String employment, String hoby, int id) {
        connect();
        prepareToUpdate();
        try {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, age);
            preparedStatement.setString(3, employment);
            preparedStatement.setString(4, hoby);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println("Send Failed");
            e.printStackTrace();
        }
        closePrepare();
        closeConnect();

    }

    public ArrayList<ArrayList<String>> getAllUsers() {
        connect();
        prepareToGet();
        ArrayList<String> userData;
        ArrayList<ArrayList<String>> massUserData = new ArrayList<ArrayList<String>>();
        int id = 1;
        while (true)
        {
            userData = getUser(id);
            if(userData.get(1) == null)
                break;
            else
                ++id;
        }

        closePrepare();
        closeConnect();
        return massUserData;
    }

    public ArrayList<String> getUser(int id) {
        connect();
        prepareToGet();
        ArrayList<String> userData = new ArrayList<String>();
        try {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                userData.add(resultSet.getString("user_name"));
                userData.add(resultSet.getString("user_age"));
                userData.add(resultSet.getString("user_employment"));
                userData.add(resultSet.getString("user_hoby"));
            }
            preparedStatement.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println("Send Failed");
            e.printStackTrace();
        }
        closePrepare();
        closeConnect();
        return userData;
    }
}
