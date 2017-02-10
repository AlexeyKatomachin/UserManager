package servlets;

import user.UserManager;
import user.userToJSON.User;
import user.userToJSON.UsersArray;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
@Path("/user")

public class UserManagerService extends HttpServlet {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UsersArray getAllUsers() {
        UsersArray usersArray = new UsersArray();
        usersArray.setUserArray(new UserManager().getAllUsers());
        return usersArray;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser (@PathParam("id") int id){
        User user = new User();
        ArrayList<String> userData = new UserManager().getUser(id);
        user.setId(id);
        user.setName(userData.get(1));
        user.setAge(Integer.getInteger(userData.get(2)));
        user.setEmployment(userData.get(3));
        user.setHoby(userData.get(4));
        return user;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser (@PathParam("id") int id, User user)
    {
        UserManager userManager = new UserManager();
        userManager.updateUser(user.getName(),user.getAge(),user.getEmployment(),user.getHoby(),id);
        return Response.status(201).entity(user).build(); // I`m not sure about this
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser (@PathParam("id") int id)
    {
        new UserManager().deleteUser(id);
        return Response.status(201).build();// I`m not sure about this
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user){
        new UserManager().createUser(user.getName(),user.getAge(),user.getEmployment(),user.getHoby());
        return Response.status(201).build();// I`m not sure about this
    }
}
