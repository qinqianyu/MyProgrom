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
        double l = 5000*10000/1000/60;
        System.out.println(Math.pow(2,10)-14);
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
