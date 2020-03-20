package tools.time;

import java.sql.Date;

public class Test {
    public static void main(String[] args) {
        long date = System.currentTimeMillis();
        Date date1 = new Date(date);
        System.out.println(date1);
    }
}
