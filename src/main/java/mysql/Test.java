package mysql;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random rand = new Random();
        int a;
        for (int j = 0; j <10 ; j++) {
            a = rand.nextInt(2);
            System.out.print(a+"\t");
        }

    }
}
