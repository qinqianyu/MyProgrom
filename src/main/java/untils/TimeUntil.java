package untils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUntil {
    public static void main(String[] args) {
        long time= Long.parseLong("1574857753");
        if(time<10000000000L)
            time*=1000;
        String format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(time));
        System.out.println(format);
    }
}
