package hashtest;

import scala.util.Random;

public class test {
    public static void main(String[] args) {
        Integer a;
        for (int j  = 0;   j<100 ;  ++j) {
            a=j;
            int i = new Random().nextInt(3);
            int i1 = Integer.valueOf(i).hashCode();
            int count0,count1,count2;
            switch (i1)
            {
                case 0:
                    count0 ++;
                    break;
                case 1:
                    count1 ++;
            }
            System.out.println(i1);
        }

    }

}
