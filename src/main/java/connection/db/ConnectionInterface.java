package connection.db;

import user.object.User;

import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public interface ConnectionInterface
{
    public User getUser(int id);
    public void createUser(User user);
    public void deleteUser(int id);
    public void updateUser(User user);
    public ArrayList<User> getAllUsers();
}
