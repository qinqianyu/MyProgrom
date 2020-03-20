package som;

import database.redis.pool.RedisPoolUtil4J;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Random;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-17 21:51
 **/
public class Maptest {
    @Test
    public void test1() {
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < 3000; i++) {
            hashMap.put(i + "", i + "");
        }

        long start = System.currentTimeMillis();
        Long count = 0L;
        for (int i = 0; i < 50000; i++) {
            hashMap.get(new Random().nextInt(3000));
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) * 1000.0 / 50000);
    }

    @Test
    public void test2() {
        //Jedis jedis = RedisPoolUtil4J.getConnection();
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < 3000; i++) {
            try (Jedis jedis = RedisPoolUtil4J.getConnection()) {
                jedis.hset("maptest", i + "", i + "");
            }
            // hashMap.put(i+"",i+"");
        }
        //jedis.close();
        System.out.println("*****");
        long start = System.currentTimeMillis();
        Long count = 0L;
        for (int i = 0; i < 500; i++) {
            Jedis jedis1 = RedisPoolUtil4J.getConnection() ;
                //jedis1.hget("maptest", String.valueOf(new Random().nextInt(3000)));
            jedis1.close();
            //hashMap.get(new Random().nextInt(3000));
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) * 1000.0 / 500);
    }
}
