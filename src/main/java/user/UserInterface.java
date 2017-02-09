package user;

import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public interface UserInterface {
    public void createUser(String name, int age, String employment, String hoby);
    public void deleteUser(int id);
    public void updateUser(String name, int age, String employment, String hoby,int id);
    public ArrayList<ArrayList<String>> getAllUsers();
    public ArrayList<String> getUser(int id);
}
