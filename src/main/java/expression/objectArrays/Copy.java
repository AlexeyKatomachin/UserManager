package expression.objectArrays;

import java.util.ArrayList;

/**
 * Created by ${Alexey} on ${09.08.2016}.
 */
public class Copy {
    public  void doCopy(ArrayList<String> newArray, ArrayList<String> oldArray){
        for (String data: oldArray) {
            newArray.add(data);
        }
    }
}
