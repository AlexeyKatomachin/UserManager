package akatomakhin.apps.usermanager.facade;

import akatomakhin.apps.usermanager.servlets.entity.client.massage.UserMassage;
import akatomakhin.apps.usermanager.entity.User;

import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public interface UserManagerInterface {
    void createUser(User user);
    void deleteUser(int id);
    UserMassage updateUser(User user);
    ArrayList<User> getAllUsers();
    User getUser(int id);
}
