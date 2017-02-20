package akatomakhin.apps.usermanager.facade;

import org.junit.Test;
import akatomakhin.apps.usermanager.entity.User;

import static org.junit.Assert.*;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class UserManagerTest {

    @Test
    public void createUserTest() {
        UserManager userManager = new UserManager();
        User user = new User();
        user.setName("Billy");
        user.setAge(25);
        user.setEmployment("dancer");
        user.setHoby("make music");
        userManager.createUser(user);
        assertNotNull(userManager.getUser(1));
        assertNotEquals(new UserManager(),userManager.getUser(1));
    }

    @Test
    public void deleteUserTest(){
        UserManager userManager = new UserManager();
        userManager.deleteUser(3);
        User user = userManager.getUser(3);
        assertEquals(0,user.getAge());
        assertNull(user.getEmployment());
        assertNull(user.getHoby());
        assertNull(user.getName());
        assertEquals(0,user.getId());
    }


    @Test
    public void getAllUsersTest() {
        UserManager userManager =  new UserManager();
        for (User user : userManager.getAllUsers()) {
            assertNotNull(user.getId());
            assertNotNull(user);
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
        User user = new User();
        user.setId(1);
        user.setName("Alex");
        user.setAge(15);
        user.setEmployment("student");
        user.setHoby("play basketball");
        userManager.updateUser(user);
        assertNotEquals(new UserManager(),userManager);
    }
}
