package sort01;

import java.util.Date;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random=new Random();
        int rand=0;//存储随机数
        int[][] arrays=new int[1000][50];//声明二维数组
        int[][] arrays1=new int[1000][50];
        //给数组赋值
        long start0=new Date().getTime();
        for(int i=0;i<arrays.length;i++){
            for(int j=0;j<arrays[i].length;j++){
                rand=random.nextInt(5000);//在0-100内随机生成一个正整数
                arrays[i][j]=rand;
                arrays1[i][j]=rand;
            }
        }
        long end0=new Date().getTime();
        long total0 = end0 - start0;
        System.out.print("***********");
        System.out.println(total0);

//        ***********************
        long start=new Date().getTime();
        for (int i = 0; i < arrays.length; i++) {
            Sorts.quickSort( arrays[i]);
        }
        long end=new Date().getTime();
        long total = end - start;
        System.out.print("***********");
        System.out.println(total);

//        *************************
        long start1=new Date().getTime();
        for (int i = 0; i < arrays.length; i++) {
            Sorts.countingSort( arrays1[i]);
        }
        long end1=new Date().getTime();
        long total1 = end1 - start1;
        System.out.print("***********");
        System.out.println(total1);
    }
}
