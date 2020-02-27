package tools.math;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;


public class JiSuanQi {
    @Test
    public void jisuanqi1() {
        double l =(double) (1882232L)/1024L/1024L;
        System.out.println(l);
    }

    @Test
    public void jisuanqi2() {
        long s=30*10000/(60*60*24);
        for (int i = 0; i < 11; i++) {
            s+=Math.pow(2,i);
        }
        System.out.println(s);
    }

    @Test
    public void jisuanqi3() {
        double result= (double)1000000/(1574746706354L - 1574745898172L);
        double result2 = (double)100000/(1574748520335L - 1574746706354L);
        System.out.println(result);
        System.out.println(result2);
    }
    @Test
    public void jisuanqi4() {
        long nextLong = ThreadLocalRandom.current().nextLong(2L << 32);
        System.out.println(nextLong);
        System.out.println(2L << 32);
        System.out.println(Long.MAX_VALUE);

    }
}
