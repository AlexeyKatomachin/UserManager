package user;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class UserManagerTest {

    @Test
    public void createUserTest() {
        UserManager userManager = new UserManager();
        userManager.createUser("Billy",25,"dancer","make music");
        assertNotNull(userManager.getUser(2));
        assertNotEquals(new UserManager(),userManager.getUser(2));
    }

    @Test
    public void deleteUserTest(){

    }

    @Test
    public void getAllUsersTest() {

    }

    @Test
    public void getUserTest() {
        UserManager userManager = new UserManager();
        assertNotNull(userManager.getUser(1));
        assertNotEquals(new UserManager(),userManager.getUser(1));
    }

    @Test

    public void updateUserTest()
    {
        UserManager userManager = new UserManager();
        userManager.updateUser("Alex",15,"student","play basketball", 1);
        assertNotEquals(new UserManager(),userManager);
    }
}
