package qingdao.works.place;

import org.junit.Test;
import qingdao.untils.RedisPoolUtil4J;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-26 16:25
 **/
public class flindNameRepeat {


    private static String infile = "C:/Users/24109/Desktop/行政区划/行政代码.txt";
    private static String key = "newnamecodehash";
    private static String key2 = "codenamehash";

    @Test
    public void test() {
        try (BufferedReader reader = new BufferedReader(new FileReader(infile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] lines = line.split("\t");
                /*try(Jedis jedis = RedisPoolUtil4J.getConnection()){
                    jedis.hset(key2,lines[0],lines[1]);
                }*/
                if (lines[1].endsWith("省") || lines[1].endsWith("市") || lines[1].endsWith("县")) {
                    insert(lines[0], lines[1]);
                    insert(lines[0], lines[1].replaceAll("[省市县]", ""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void insert(String code, String name) {
        try (Jedis jedis = RedisPoolUtil4J.getConnection()) {
            if (jedis.hexists(key, name)) {
                String hget = jedis.hget(key, name);
                System.out.println(jedis.hget(key2, hget) + "---" + jedis.hget(key2, code));
            } else {
                jedis.hset(key, name, code);
            }
        }
    }

    @Test
    public void test1() {
        try (Jedis jedis = RedisPoolUtil4J.getConnection()) {
            Set<String> hvals =  jedis.hkeys(key);
          hvals.forEach(System.out::println);
        }
    }
}
