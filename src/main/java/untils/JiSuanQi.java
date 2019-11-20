package untils;

import org.junit.Test;

public class JiSuanQi {
    public static void main(String[] args) {
        double[] f = {0.1, 0.01, 0.001};
        for (double tmp : f) {
            System.out.println(hashmenmber(tmp));
        }
    }


    private static int hashmenmber(double value) {
        return (int) Math.ceil(log(Math.E, 2) * log(1 / value, 2));
    }

    private static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }

    @Test
    public void jisuanqi1() {
        double l = 298726561.0 / 30 / 10000 / 365;
        System.out.println(l);
    }

    @Test
    public void jisuanqi2() {
        double log = log(597453122, 2);
        System.out.println(log);
    }

    @Test
    public void jisuanqi3() {
        int i = 30 * 10000 * 365 * 2;
        System.out.println(i);
    }

}
