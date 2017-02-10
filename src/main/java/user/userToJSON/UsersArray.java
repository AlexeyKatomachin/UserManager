package user.userToJSON;

import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class UsersArray {
    public String userArray = "";

    public void setUserArray(ArrayList<ArrayList<String>> userDataArray) {
        for (ArrayList<String> userData : userDataArray) {
            User user = new User();
            user.setId(Integer.getInteger(userData.get(0)));
            user.setName(userData.get(1));
            user.setAge(Integer.getInteger(userData.get(2)));
            user.setEmployment(userData.get(3));
            user.setHoby(userData.get(4));
            userArray += ", " + user;
        }
    }

    public String getUserArray() {
        return userArray;
    }

    @Override
    public String toString()
    {
        return "{ " + userArray + " }";
    }

}
