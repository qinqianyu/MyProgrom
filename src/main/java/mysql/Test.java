package mysql;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random rand = new Random();
        int a;
        for (int j = 0; j <10 ; j++) {
            a = rand.nextInt(1);
        }
        Guanxi guanxi = new Guanxi(1,2,3,4,5,"s1","s2","s3","s4","s5");
        Map<String, Object> map = entityToMap(guanxi);
        String s = JSON.toJSONString(map);
        System.out.println(s);
        System.out.println(map.toString());
    }
    public static Map<String, Object> entityToMap(Object object) {
        Map<String, Object> map = new HashMap();
        for (Field field : object.getClass().getDeclaredFields()){
            try {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                Object o = field.get(object);
                map.put(field.getName(), o);
                field.setAccessible(flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
