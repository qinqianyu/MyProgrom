package tools.time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author jxk
 * @Date 2019/12/11 9:24
 * @Description 解析毫秒时间戳
 **/
public class TimeUntil {
    public static void main(String[] args) {
        long time= Long.parseLong("1574857753");
        if(time<10000000000L)
            time*=1000;
        String format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(time));
        System.out.println(format);
    }
}
