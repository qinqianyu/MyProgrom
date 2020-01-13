package tools.text;

import java.util.ArrayList;
import java.util.Arrays;

public class Stringreplase {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(
                "/"
                ));

        String oldstr ="C:\\Users\\24109\\Desktop\\同步\\root";
        String oldword = "\\";
        for (String str:list) {
            String replace = oldstr.replace(oldword, str);
            System.out.println(replace);
        }
    }
}