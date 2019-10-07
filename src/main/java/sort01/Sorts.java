package sort01;

import java.util.Arrays;

class Sorts {

    /**
     * 归并排序
     *
     * @param array
     * @return
     */
    static int[] MergeSort1(int[] array) {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge1(MergeSort1(left), MergeSort1(right));
    }

    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     *
     * @param left
     * @param right
     * @return
     */
    static private int[] merge1(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[j++];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }


    /*
    归并排序2
    */
    static void MergeSort2(int[] a) {
        int low = 0;
        int high = a.length - 1;
        MergeSort2(a, low, high);
    }

    static private void MergeSort2(int[] a, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            MergeSort2(a, low, mid);
            MergeSort2(a, mid + 1, high);
            //左右归并
            merge2(a, low, mid, high);
        }
    }

    static private void merge2(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int x = 0; x < temp.length; x++) {
            a[x + low] = temp[x];
        }
    }
}