package servlets;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import user.UserManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class UserManagerServlet extends HttpServlet {

    public void doGet(HttpServletResponse res, HttpServletRequest req) {

    }

    public void doPost(HttpServletResponse res,HttpServletRequest req) throws IOException{

        /*
        *create String write of the request
         */
        InputStreamReader inputStreamReader = new InputStreamReader(req.getInputStream());
        String jsonString = "";
        int c;
        while ((c = inputStreamReader.read()) >= 0) {
            jsonString += (char) c;
        }

        /*
        *create json Jackson Object
         */
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(jsonString, JsonNode.class);

        /*
        *create user data
         */
        String name = rootNode.get("name").getTextValue();
        int age = Integer.getInteger(rootNode.get("age").getTextValue());
        String employment  = rootNode.get("employment").getTextValue();
        String hoby = rootNode.get("hoby").getTextValue();
        /*
        *create User
         */
        new UserManager().createUser(name,age,employment,hoby);
    }

    public void doPut(HttpServletResponse res,HttpServletRequest req)throws IOException{
        /*
        *create String write of the request
         */
        InputStreamReader inputStreamReader = new InputStreamReader(req.getInputStream());
        String jsonString = "";
        int c;
        while ((c = inputStreamReader.read()) >= 0) {
            jsonString += (char) c;
        }

        /*
        *create json Jackson Object
         */
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(jsonString, JsonNode.class);

        /*
        *create user data
         */
        String name = rootNode.get("name").getTextValue();
        int age = Integer.getInteger(rootNode.get("age").getTextValue());
        String employment  = rootNode.get("employment").getTextValue();
        String hoby = rootNode.get("hoby").getTextValue();
        /*
        *update User
         */
        new UserManager().updateUser(name,age,employment,hoby,id);
    }

    public void doDelete(HttpServletResponse res, HttpServletRequest req){
        
    }
}
