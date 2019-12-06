package math;

import org.junit.Test;

public class MathUntils {

    private static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }

    static String toBinary(int num, int n) {
        String str = "";
        while (num != 0) {
            str = num % 2 + str;
            num = num / 2;
        }
        while (str.length() < n) {
            str = "0" + str;
        }
        System.out.println(str);
        return str;
    }

    static String toBinary(int num) {
        return toBinary(num, 8);
    }

    @Test
    public void test() {
        toBinary(6);
    }
}
