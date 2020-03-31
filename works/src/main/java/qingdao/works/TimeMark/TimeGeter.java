package qingdao.works.TimeMark;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-27 08:44
 **/
public class TimeGeter {

    @Test
    public void test() {
        int i = 1585183742 - 1585183243;
        System.out.println(9304.0 / i);
        System.out.println(9304.0 / i * 24 * 3600 / 10000);
    }

    @Test
    public void test1() {
        List<Long> timecount = Arrays.asList(0L, 1L);
        System.out.println(timecount.get(0));
        timecount.set(0, timecount.get(0) + 3);
        System.out.println(timecount.get(0));
    }

    @Test
    public void test2() {
        List<Integer> integers = Arrays.asList(1005, 8976, 43854, 392764, 105490, 244985, 129785, 652);
        int sum = integers.stream().mapToInt(x -> x).sum();
        System.out.println(sum);
    }
    @Test
    public void test3() {
        List<Integer> integers = Arrays.asList(144, 278, 798, 9988, 2668, 6178, 2758, 23);
        List<Integer> integers1 = Arrays.asList(696, 6097, 18630, 254001, 63334, 148540, 66278, 448);
        int sum = integers.stream().mapToInt(x -> x).sum();
        int sum1 = integers1.stream().mapToInt(x -> x).sum();
        System.out.println(sum1-sum);
    }
    @Test
    public void test4() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        String yearMonth = localDate.toString().replaceAll("-", "");
        String id = yearMonth ;
        System.out.println(Long.valueOf(id));
    }
    @Test
    public void test5() {
        LocalDateTime now = LocalDateTime.now();
        long currentTimeMillis = System.currentTimeMillis();
        Long time = now.toEpochSecond(ZoneOffset.of("+8"));
        System.out.println(currentTimeMillis);
        System.out.println(now);
        System.out.println(time);
        System.out.println(time%10000000L);
    }
    @Test
    public void test6() {
        System.out.println(10000000L/3600/24);
        Date date = new Date(1585274716000L);
        System.out.println(date);
    }

    @Test
    public void test7() {
        long time= Long.parseLong("1585645659");
        if(time<10000000000L)
            time*=1000;
        String format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(time));
        String format1 = new SimpleDateFormat("HH:mm:ss").format(new Date(Long.parseLong("1574857753")));
        System.out.println(format);
       // System.out.println(format1);
    }
}
