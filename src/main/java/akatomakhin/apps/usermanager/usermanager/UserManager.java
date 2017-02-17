package akatomakhin.apps.usermanager.usermanager;

import akatomakhin.apps.usermanager.connection.db.mysql.MySQLUserDAO;
import akatomakhin.apps.usermanager.user.object.User;

import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class UserManager implements UserManagerInterface {

    /*
    *create User
     */
    public void createUser(User user) {
        new MySQLUserDAO().createUser(user);
    }

    /*
    *Delete User by ID
     */
    public void deleteUser(int id) {
        new MySQLUserDAO().deleteUser(id);
    }

    /*
    *Update user
     */
    public void updateUser(User user) {
        new MySQLUserDAO().updateUser(user);
    }

    /*
    * make users Array
     */
    public  ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new MySQLUserDAO().getAllUsers();;
        return allUsers;
    }

    /*
    * make Object user
     */
    public  User getUser(int id) {
        User user = new MySQLUserDAO().getUser(id);
        return user;
    }
}
