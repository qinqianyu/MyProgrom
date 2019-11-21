package untils;

import org.junit.Test;


public class JiSuanQi {
    public static void main(String[] args) {
        double size = 5000000;
        double[] f = {0.1, 0.01, 0.001};
        for (double tmp : f) {
            System.out.println(hashmenmber(tmp));
            System.out.println(bitsize(size, tmp));
        }
    }

    /**
     * @param value 期望的错误率(假阳性)
     * @return 最佳哈希函数的数目
     */
    private static double hashmenmber(double value) {
        return -Math.log(value) / Math.log(2);
    }

    /**
     * @param size  估计的集合元素总数
     * @param value 期望的错误率(假阳性)
     * @return 需要的bit数组的大小
     */
    private static int bitsize(double size, double value) {
        return (int) Math.ceil(-Math.log(value) * size / Math.pow(Math.log(2), 2));
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

    private static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }
}
