package database.redis.conectTest;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.*;

public class RedisTest {
    public static Jedis getJedis(){
        Jedis jedis = new Jedis("192.168.20.138",6379);
        return jedis;
    }
    //    public static void setJedis(String key,String value){
//        Jedis jedis = getJedis();
//        jedis.set(key,value);
//        jedis.close();
//    }
    public static String getJString(String key){
        Jedis jedis = getJedis();
        String s = jedis.get(key);
        jedis.close();
        return s;
    }
    public static void setObject(String key,Object ob){
        String s = JSONArray.toJSONString(ob);
        Jedis jedis = getJedis();
        jedis.set(key,s);
        jedis.close();
    }

    public static byte[] writeObject(Object ob){
        byte[] bytes=null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(ob);
            bytes=byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
    public static Object readObject(byte[] bytes){
        Object ob=null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
            ob = inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ob;
    }


    @Test
    public  void  teststudent(){
        Student student = new Student(1,"张三","男",15);
        setObject("1",student);
        String s = getJString("1");
        Student student1 = JSONArray.parseObject(s, Student.class);
    }

    @Test
    public  void  testPool(){
        Student student = new Student(2,"张三","男",15);
        Jedis jedis = RedisPoolUtil.getPool().getResource();
        jedis.set("2",JSONArray.toJSONString(student));
        jedis.expire("2",20);
        String s = jedis.get("2");
        Student student1 = JSONArray.parseObject(s, Student.class);
        System.out.println(student1);
    }

    @Test
    public  void  testincy(){
        Jedis jedis = RedisPoolUtil.getPool().getResource();
        String age = jedis.set("age", "3");
        System.out.println(age);
    }




    public static void main(String[] args) {
        Student student = new Student(1,"张三","男",15);
        byte[] bytes = writeObject(student);
        Jedis jedis = getJedis();
        jedis.set("ob".getBytes(),bytes);
        byte[] bytes1 = jedis.get("ob".getBytes());
        Object ob= readObject(bytes1);
        Student ob1 = (Student) ob;
        System.out.println(ob1.getSname());
    }
}