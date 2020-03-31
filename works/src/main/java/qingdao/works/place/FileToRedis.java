package qingdao.works.place;

import org.junit.Test;
import qingdao.untils.RedisPoolUtil4J;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-26 15:28
 **/
public class FileToRedis {
    private static String infile = "C:/Users/24109/Desktop/行政区划/行政代码.txt";
    private static String key = "newnamecodehash";

    @Test
    public void test() {
        try (BufferedReader reader = new BufferedReader(new FileReader(infile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] lines = line.split("\t");
                insert(lines[0], lines[1]);
                insert(lines[0], lines[1].replaceAll("[省市县]", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void insert(String code, String name) {
        try (Jedis jedis = RedisPoolUtil4J.getConnection()) {
            if (jedis.hexists(key, name)) {
                String hget = jedis.hget(key, name);
                if (!hget.contains(code)) {
                    jedis.hset(key, name, hget + ";" + code);
                }
            } else {
                jedis.hset(key, name, code);
            }
        }
    }


    @Test
    public void test1() {
        final String hget = RedisPoolUtil4J.getConnection().hget(key, "桥西区");
        System.out.println(hget);
    }

    @Test
    public void test2() {
        try (Jedis jedis = RedisPoolUtil4J.getConnection()) {
           jedis.hset(key,"青岛西海岸新区","370211");
        }
    }
}
