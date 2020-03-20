package som;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-17 20:53
 **/
public abstract class Student {
    protected static List<String> id = new ArrayList<String>();

    static {
        for(int i=0;i<3;i++){
            id.add(i+"");
        }
    }
}
