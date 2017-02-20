package akatomakhin.apps.usermanager.facade;

import akatomakhin.apps.usermanager.dao.mysql.MySQLUserDAO;
import akatomakhin.apps.usermanager.servlets.entity.client.massage.UserMassage;
import akatomakhin.apps.usermanager.entity.User;

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
    public UserMassage updateUser(User user) {
        UserMassage userMassage = new UserMassage();
        if (user.getAge() == 0){
            userMassage.setMassage("Enter age");
            return userMassage;
        } else if((user.getAge()<18)){
            userMassage.setMassage("Age less then 18");
            return userMassage;
        } else if(user.getName() == ""){
            userMassage.setMassage("Enter name");
            return userMassage;
        } else if (user.getName().length() > 20) {
            userMassage.setMassage("Name is so big. Sorry ):");
            return userMassage;
        } else  {
            new MySQLUserDAO().updateUser(user);
            return new UserMassage();
        }
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
