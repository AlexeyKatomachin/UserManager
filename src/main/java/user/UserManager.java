package user;

import connection.db.UserConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class UserManager extends UserConnection implements UserInterface {

    /*
    *create User
     */
    public void createUser(String name, int age, String employment, String hoby) {
        connect();
        prepareToCreate();
        try {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, employment);
            preparedStatement.setString(4, hoby);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Send Failed");
            e.printStackTrace();
        }
        closePrepare();
        closeConnect();
    }

    /*
    *Delete User by ID
     */
    public void deleteUser(int id) {
        connect();
        prepareToDelete();
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Send Failed");
            e.printStackTrace();
        }
        closePrepare();
        closeConnect();
    }

    /*
    *Update user
     */
    public void updateUser(String name, int age, String employment, String hoby, int id) {
        connect();
        prepareToUpdate();
        try {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, employment);
            preparedStatement.setString(4, hoby);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Send Failed");
            e.printStackTrace();
        }
        closePrepare();
        closeConnect();

    }

    /*
    * make double Object Array for all user from the DB
    *from each elements is one Row
     */
    public ArrayList<ArrayList<String>> getAllUsers() {
        connect();
        prepareToGet();
        ArrayList<String> userData;
        ArrayList<ArrayList<String>> massUserData = new ArrayList<ArrayList<String>>();
        int id = 1;
        while (true) {
            userData = getUser(id);             /*get one row*/
            if (userData.get(1) == null)         /*if it`s a null id of row*/
                break;                          /*we break out*/
            else                                /*else*/
                ++id;                           /*we get next ID*/
            massUserData.add(userData);         /*and add this row to the object array*/
        }
        closePrepare();
        closeConnect();
        return massUserData;
    }

    /*
    *Object Array describe one row from DB
     */
    public ArrayList<String> getUser(int id) {
        connect();
        prepareToGet();
        ArrayList<String> userData = new ArrayList<String>();
        try {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userData.add(resultSet.getString("user_id"));
                userData.add(resultSet.getString("user_name"));
                userData.add(resultSet.getString("user_age"));
                userData.add(resultSet.getString("user_employment"));
                userData.add(resultSet.getString("user_hoby"));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Send Failed");
            e.printStackTrace();
        }
        closePrepare();
        closeConnect();
        return userData;
    }
}
