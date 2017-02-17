package facade;

import connection.db.mysql.MySQLConnection;
import user.object.User;

import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class UserManager implements UserManagerInterface {

    /*
    *create User
     */
    public void createUser(User user) {
        new MySQLConnection().createUser(user);
    }

    /*
    *Delete User by ID
     */
    public void deleteUser(int id) {
        new MySQLConnection().deleteUser(id);
    }

    /*
    *Update user
     */
    public void updateUser(User user) {
        new MySQLConnection().updateUser(user);
    }

    /*
    * make users Array
     */
    public  ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new MySQLConnection().getAllUsers();;
        return allUsers;
    }

    /*
    * make Object user
     */
    public  User getUser(int id) {
        User user = new MySQLConnection().getUser(id);
        return user;
    }
}
