package com.jxk.database.jpa.coo;

import org.junit.Test;

import java.util.*;

public class CTest {
    @Test
    public void test1() {
        Stu[] stus = new Stu[]{Stu.builder().age(1).build()};
        for (int i = 0; i < 5; i++) {
            stus[i] = Stu.builder().age(i).build();
        }
        Arrays.stream(stus).forEach(System.out::println);
    }

    @Test
    public void test2() {
        ArrayList<Stu> stus = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            stus.add(Stu.builder().age(i).build());
        }
        int index = 2;
        for (Stu stu : stus) {
            System.out.println(stu);
            if (index == 2) {
                stus.remove(stu);
            }
            index++;
        }

    }

    @Test
    public void test3() {
        ArrayList<Stu> stus = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            stus.add(Stu.builder().age(i).build());
        }
        for (int i = 0; i < stus.size(); i++) {
            System.out.println(stus.get(i));
            if (i == 2) {
                stus.remove(stus.get(i));
            }
        }
    }


    @Test
    public void test4() {
        ArrayList<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            integers.add(i * 10);
        }
        integers.add(20);
        System.out.println(integers);

        integers.remove(new Integer(20));
        System.out.println(integers);
        integers.remove(new Integer(20));
        System.out.println(integers);
        integers.remove(1);
        System.out.println(integers);
    }

    @Test
    public void test5() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(100);
        integers.add(100);
        System.out.println(integers.get(0) == integers.get(1));
    }


    @Test
    public void test6() {
        Stu build = Stu.builder().age(15).build();
        HashSet<Stu> stus = new HashSet<>();
        stus.add(build);
        System.out.println(stus.contains(build));
        build.setAge(16);
        System.out.println(stus.contains(build));
        boolean contains = false;
        for (Stu stu : stus) {
            if (stu.equals(build)) {
                contains = true;
            }
        }
        System.out.println(contains);
    }


    @Test
    public void test7() {
        ArrayList integers = new ArrayList<>();
        integers.add(55);
        integers.add("66");

        for (Object integer : integers) {
            String integer1 = (String) integer;
            System.out.println(integer1);
        }
    }

    @Test
    public void test8() {
        TreeSet<String> strings = new TreeSet<>((o1, o2) -> {
            int n1 = o1.length() - o2.length();
            int n2 = o1.compareTo(o2);
            return n1 == 0 ? n2 : -n1;
        });
        strings.add("nanjing");
        strings.add("suzhou");
        strings.add("xian");
        strings.add("shanghai");
        System.out.println(strings);

    }


    @Test
    public void test9() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("cn", "中华");
        hashMap.put("eu", "欧洲");
        hashMap.put("au", "美国");
        hashMap.put("cn", "中国");

        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }


    }


    @Test
    public void test10() {
        HashMap<Stu, String> hashMap = new HashMap<>();
        HashSet<Stu> hashSet = new HashSet<>();
        Stu stu1 = Stu.builder().age(18).flag(1).build();
        Stu stu2 = Stu.builder().age(18).flag(2).build();
        hashSet.add(stu1);
        hashSet.add(stu2);
        System.out.println(hashSet);
        hashMap.put(stu1, "nan");
        System.out.println(hashMap);
        hashMap.put(stu2, "bei");
        System.out.println(hashMap);
    }


    @Test
    public void test11() {
        ArrayList<String> list = new ArrayList<>(10);
        list.add("1");
        list.add("2");
        list.add("3");
        ArrayList<String> dest = new ArrayList<>();
        dest.add("");
        dest.add("");
        System.out.println(dest);
        Collections.copy(dest, list);
        System.out.println(dest);
    }

    @Test
    public void test12() {
        ArrayList<Stu> list = new ArrayList<Stu>();
        list.add(Stu.builder().name("张三").age(1).build());
        list.add(Stu.builder().name("张四").age(5).build());
        list.add(Stu.builder().name("张五").age(2).build());

        System.out.println(list);
        Collections.sort(list, (o1, o2) -> {
            int i = o1.getAge().compareTo(o2.getAge());
            int b = o1.getName().compareTo(o2.getName());
            return i == 0 ? b : i;
        });
        System.out.println(list);


    }


}
