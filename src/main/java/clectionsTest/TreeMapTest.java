package clectionsTest;

import org.apache.lucene.util.RamUsageEstimator;
import org.junit.Test;

import java.util.HashMap;
import java.util.TreeMap;

public class TreeMapTest {
    @Test
    public void test1() {
        HashMap<String, String> hashMap = new HashMap<>();
        TreeMap<String, String> treeMap = new TreeMap<>();
        String key;
        String value;
        int[] f = {5, 10, 100,1000,10000};
        for (int tmp : f) {
            for (int i = 0; i < tmp; i++) {
                key = "name" + i;
                value = "value" + i;
                hashMap.put(key, value);
                treeMap.put(key, value);
            }
            System.out.println("-----"+tmp+"-----");
            System.out.println(RamUsageEstimator.sizeOf(hashMap) + "--" + RamUsageEstimator.humanSizeOf(hashMap));
            System.out.println(RamUsageEstimator.sizeOf(treeMap) + "--" + RamUsageEstimator.humanSizeOf(treeMap));
        }
    }
    @Test
    public void test2() {
        String[] strings = new String[65535 + 1];
        System.out.println(new Character('池').hashCode()-new Character('江').hashCode());
        System.out.println(RamUsageEstimator.sizeOf(strings) + "--" + RamUsageEstimator.humanSizeOf(strings));

    }

}
