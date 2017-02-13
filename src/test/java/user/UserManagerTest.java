package user;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class UserManagerTest {

    @Test
    public void createUserTest() {
        UserManager userManager = new UserManager();
        userManager.createUser("Billy",25,"dancer","make music");
        assertNotNull(userManager.getUser(1));
        assertNotEquals(new UserManager(),userManager.getUser(1));
    }

    @Test
    public void deleteUserTest(){
        UserManager userManager = new UserManager();
        userManager.deleteUser(3);
        for (String userData:userManager.getUser(3)) {
            assertNull(userData);
        }
    }


    @Test
    public void getAllUsersTest() {
        UserManager userManager =  new UserManager();
        for (ArrayList<String> userArray : userManager.getAllUsers()) {
            assertNotNull(userArray.get(0));
            assertNotNull(userArray);
        }
    }



    @Test
    public void getUserTest() {
        UserManager userManager = new UserManager();
        assertNotNull(userManager.getUser(6));
        assertNotEquals(new UserManager(),userManager.getUser(0));
    }

    @Test

    public void updateUserTest()
    {
        UserManager userManager = new UserManager();
        userManager.updateUser("Alex",15,"student","play basketball", 1);
        assertNotEquals(new UserManager(),userManager);
    }
}
