package user.specialFunctions;

import connection.db.UserConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class Functions extends UserConnection {
    public int [] getAllUsersId(){
        connect();
        prepareToGetAllID();
        int [] usersID = null;
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (getQuantityID() != 0){
                usersID = new int[getQuantityID()];
                for (int i = 0; i < getQuantityID(); ++i){
                    if (resultSet.next()) {
                        usersID[i] = resultSet.getInt("user_id");
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return usersID;
    }

    public int getQuantityID() {
        connect();
        prepareToGetQuantityID();
        int quantityID = 0;
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                quantityID = resultSet.getInt("quantityId");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return quantityID;
    }
}
