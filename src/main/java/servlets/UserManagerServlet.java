package servlets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;
import user.UserManager;
import user.userToJSON.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class UserManagerServlet extends HttpServlet {

    @JsonIgnore
    public Boolean isTaxable() {
        return null;
    }

    /* get user*/
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        /* find index of last '/' symbol*/
        int lastSymbol = 0;
        for (int i = 0; i < req.getRequestURL().length(); ++i) {
            if (req.getRequestURL().charAt(i) == '/') {
                lastSymbol = i + 1;

            }
        }

        ObjectMapper mapper = new ObjectMapper();
        if ((lastSymbol != req.getRequestURL().length() && (lastSymbol > req.getRequestURL().indexOf("user")))) {
        /* get Id from Url and get User with this Url*/
            String userIdStr = req.getRequestURL().substring(lastSymbol);
            int userId = Integer.valueOf(userIdStr);

        /* make JSON String*/
            mapper.writeValue(res.getOutputStream(), new UserManager().getUser(userId));

        }else if ((lastSymbol == req.getRequestURL().length() || (lastSymbol <= req.getRequestURL().indexOf("user")))){
            mapper.writeValue(res.getOutputStream(), new UserManager().getAllUsers());
        }

        res.getOutputStream().flush();
        res.getOutputStream().close();
    }

    /* create user*/
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        /* read JSON data */
        ObjectMapper mapper = new ObjectMapper();

        User user = mapper.readValue(req.getInputStream(), User.class);

        new UserManager().createUser(user);
    }

    /* delete user*/
    public void doDelete(HttpServletRequest req, HttpServletResponse res) {

        int lastSymbol = 0;
        for (int i = 0; i < req.getRequestURL().length(); ++i) {
            if (req.getRequestURL().charAt(i) == '/') {
                lastSymbol = i + 1;

            }
        }

        if ((lastSymbol != req.getRequestURL().length() && (lastSymbol > req.getRequestURL().indexOf("user")))) {
            String userIdStr = req.getRequestURL().substring(lastSymbol);
            int userId = Integer.valueOf(userIdStr);
            new UserManager().deleteUser(userId);
        }
    }

    /* update user*/
    public void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {

        int lastSymbol = 0;
        for (int i = 0; i < req.getRequestURL().length(); ++i) {
            if (req.getRequestURL().charAt(i) == '/') {
                lastSymbol = i + 1;

            }
        }

        /* Get data*/
        if ((lastSymbol != req.getRequestURL().length() && (lastSymbol > req.getRequestURL().indexOf("user")))) {
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(req.getInputStream(),User.class);
            String userIdStr = req.getRequestURL().substring(lastSymbol);
            int userId = Integer.valueOf(userIdStr);
            user.setId(userId);
            new UserManager().updateUser(user);
        }
    }
}
