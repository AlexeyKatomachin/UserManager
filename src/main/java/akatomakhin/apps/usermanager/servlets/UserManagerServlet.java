package akatomakhin.apps.usermanager.servlets;

import akatomakhin.apps.usermanager.facade.UserManager;
import akatomakhin.apps.usermanager.servlets.entity.client.massage.type.ErrorMassage;
import akatomakhin.apps.usermanager.entity.User;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;

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
    public void  doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        /* find index of last '/' symbol*/
        int lastSymbol = 0;
        for (int i = 0; i < req.getRequestURL().length(); ++i) {
            if (req.getRequestURL().charAt(i) == '/') {
                lastSymbol = i + 1;

            }
        }

        ObjectMapper mapper = new ObjectMapper();
        if ((lastSymbol != req.getRequestURL().length() && (lastSymbol > req.getRequestURL().indexOf("akatomakhin/apps/usermanager/user")))) {
        /* get Id from Url and get User with this Url*/
            String userIdStr = req.getRequestURL().substring(lastSymbol);
            int userId = Integer.valueOf(userIdStr);

        /* make JSON Stream*/
            mapper.writeValue(res.getOutputStream(),new UserManager().getUser(userId));

        }else if ((lastSymbol == req.getRequestURL().length() || (lastSymbol <= req.getRequestURL().indexOf("akatomakhin/apps/usermanager/user")))){
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

        if ((lastSymbol != req.getRequestURL().length() && (lastSymbol > req.getRequestURL().indexOf("akatomakhin/apps/usermanager/user")))) {
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
        if ((lastSymbol != req.getRequestURL().length() && (lastSymbol > req.getRequestURL().indexOf("akatomakhin/apps/usermanager/user")))) {
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(req.getInputStream(),User.class);
            String userIdStr = req.getRequestURL().substring(lastSymbol);
            int userId = Integer.valueOf(userIdStr);
            user.setId(userId);
            if( new UserManager().updateUser(user).getMassage() != null){
                ErrorMassage errorMassage = new ErrorMassage();
                errorMassage.setError(new UserManager().updateUser(user));
                res.addHeader("Content-Type","application/json");
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                mapper.writeValue(res.getOutputStream(),errorMassage);
                res.getOutputStream().flush();
                res.getOutputStream().close();
            }
        }
    }
}
