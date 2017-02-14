package servlets;

import user.UserManager;
import user.userToJSON.User;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
@Path("/user")

public class UserManagerServletJersey extends HttpServlet {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getAllUsers() {
        return new UserManager().getAllUsers();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser (@PathParam("id") int id){
        User user = new User();
        User userData = new UserManager().getUser(id);
        user.setId(id);
        user.setName(userData.getName());
        user.setAge(userData.getAge());
        user.setEmployment(userData.getEmployment());
        user.setHoby(userData.getHoby());
        return user;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser (@PathParam("id") int id, User user)
    {
        new UserManager().updateUser(user);
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
        new UserManager().createUser(user);
        return Response.status(201).build();// I`m not sure about this
    }
}
