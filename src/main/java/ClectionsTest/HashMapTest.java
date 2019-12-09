package ClectionsTest;

import org.junit.Test;

import java.util.*;

public class HashMapTest {
    @Test
    public void test1() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("java", 13);
        hashMap.put("matlab", 13);
        hashMap.put("python", 13);
        Set<String> keySet = hashMap.keySet();
        System.out.println(keySet.size());
        System.out.println(keySet.toString());
    }

    @Test
    public void test2() {
        //创建一个集合
        Collection books = new HashSet();
        books.add("轻量级J2EE企业应用实战");
        books.add("Struts2权威指南");
        books.add("基于J2EE的Ajax宝典");
        //获取books集合对应的迭代器
        Iterator<String> it = books.iterator();
        while (it.hasNext()) {
            String book = it.next();
            System.out.println(book);
            if (book.equals("Struts2权威指南")) {

                //使用Iterator迭代过程中，不可修改集合元素,下面代码引发异常
                //books.remove(book);
            }
            //对book变量赋值，不会改变集合元素本身
            book = "测试字符串";
        }
        System.out.println(books);
    }


}
