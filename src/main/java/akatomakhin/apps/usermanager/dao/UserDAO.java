package akatomakhin.apps.usermanager.dao;

import akatomakhin.apps.usermanager.entity.User;

import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public interface UserDAO
{
    public User getUser(int id);
    public void createUser(User user);
    public void deleteUser(int id);
    public void updateUser(User user);
    public ArrayList<User> getAllUsers();
}
