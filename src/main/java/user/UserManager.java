package user;

import connection.db.UserConnection;
import user.specialFunctions.Functions;
import user.userToJSON.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class UserManager implements UserInterface {
    private UserConnection userConnection = new UserConnection();

    /*
    *create User
     */
    public void createUser(User user) {
        userConnection.connect();
        userConnection.prepareToCreate();
        try {
            userConnection.preparedStatement.setString(1, user.getName());
            userConnection.preparedStatement.setInt(2, user.getAge());
            userConnection.preparedStatement.setString(3, user.getEmployment());
            userConnection.preparedStatement.setString(4, user.getHoby());
            userConnection.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Send Failed");
            e.printStackTrace();
        }
        userConnection.closePrepare();
        userConnection.closeConnect();

    }

    /*
    *Delete User by ID
     */
    public void deleteUser(int id) {
        userConnection.connect();
        userConnection.prepareToDelete();
        try {
            userConnection.preparedStatement.setInt(1, id);
            userConnection.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Send Failed");
            e.printStackTrace();
        }
        userConnection.closePrepare();
        userConnection.closeConnect();
    }

    /*
    *Update user
     */
    public void updateUser(User user) {
        userConnection.connect();
        userConnection.prepareToUpdate();
        try {
            userConnection.preparedStatement.setString(1, user.getName());
            userConnection.preparedStatement.setInt(2, user.getAge());
            userConnection.preparedStatement.setString(3, user.getEmployment());
            userConnection.preparedStatement.setString(4, user.getHoby());
            userConnection.preparedStatement.setInt(5, user.getId());
            userConnection.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Send Failed");
            e.printStackTrace();
        }
        userConnection.closePrepare();
        userConnection.closeConnect();

    }

    /*
    * make double Object Array for all user from the DB
    *from each elements is one Row
     */
    public ArrayList<User> getAllUsers() {
        userConnection.connect();
        userConnection.prepareToGet();
        Functions functions = new Functions();
        ArrayList<User> massUserData = new ArrayList<User>();
        for (int i = 0; i < functions.getQuantityID(); ++i){
            massUserData.add(getUser(functions.getAllUsersId()[i]));
        }
        userConnection.closePrepare();
        userConnection.closeConnect();
        return massUserData;
    }

    /*
    *Object Array describe one row from DB
     */
    public User getUser(int id) {
        userConnection.connect();
        userConnection.prepareToGet();
        User user = new User();
        try {
            userConnection.preparedStatement.setInt(1, id);
            ResultSet resultSet = userConnection.preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("user_name"));
                user.setAge(resultSet.getInt("user_age"));
                user.setEmployment(resultSet.getString("user_employment"));
                user.setHoby(resultSet.getString("user_hoby"));

            }
        } catch (SQLException e) {
            System.out.println("Send Failed");
            e.printStackTrace();
        }
        userConnection.closePrepare();
        userConnection.closeConnect();
        return user;
    }
}
