package sort01;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] a={8,2,7,5,8,1,6};
        Sorts.MergeSort2(a);
        Arrays.stream(a).forEach((t)-> System.out.print(t+"\t"));
    }
}
