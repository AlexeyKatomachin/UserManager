package connection.db;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public interface UserConnectionInterface
{
    public void connect();
    public void closeConnect();
    public void prepareToGet();
    public void prepareToCreate();
    public void prepareToDelete();
    public void prepareToUpdate();
    public void closePrepare();
}
