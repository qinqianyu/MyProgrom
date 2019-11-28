package untils;

import org.junit.Test;


public class JiSuanQi {
    @Test
    public void jisuanqi1() {
        double l =(double) (3023-2938)/(3123-2938);
        System.out.println(l*2);
    }

    @Test
    public void jisuanqi2() {
        long s=0;
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

    private static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }
}
