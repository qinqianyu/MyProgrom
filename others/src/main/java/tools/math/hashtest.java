package tools.math;


import java.util.Random;

public class hashtest {
    public static void main(String[] args) {
        Integer a;
        Integer count0 = 0, count1 = 0, count2 = 0;
        for (int j = 0; j < 100; ++j) {
            a = j;
            int i = new Random().nextInt(3);
            int i1 = Integer.valueOf(i).hashCode();
            switch (i1) {
                case 0:
                    count0 += 1;
                    break;
                case 1:
                    count1 += 1;
                    break;
                case 2:
                    count2 += 1;
            }
            System.out.println(i1);
        }
        System.out.println(count0 + "**" + count1 + "**" + count2);
    }

}
