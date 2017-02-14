package user.specialFunctions;

import connection.db.UserConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */

/*
* This class make equations for get quantity ID
* and get array of real users ID
*/

public class Functions {
    private UserConnection userConnection = new UserConnection();

    /*
    * get Id of all users
    */
    public int[] getAllUsersId() {
        userConnection.connect();
        userConnection.prepareToGetAllID();
        int[] usersID = null;
        try {
            ResultSet resultSet = userConnection.preparedStatement.executeQuery();
            if (getQuantityID() != 0) {
                usersID = new int[getQuantityID()];
                for (int i = 0; i < getQuantityID(); ++i) {
                    if (resultSet.next()) {
                        usersID[i] = resultSet.getInt("user_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userConnection.closeConnect();
        userConnection.closePrepare();
        return usersID;
    }

    /*
    * get quantity of ID
    */
    public int getQuantityID() {
        userConnection.connect();
        userConnection.prepareToGetQuantityID();
        int quantityID = 0;
        try {
            ResultSet resultSet = userConnection.preparedStatement.executeQuery();
            if (resultSet.next()) {
                quantityID = resultSet.getInt("quantityId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userConnection.closeConnect();
        userConnection.closePrepare();
        return quantityID;
    }
}
