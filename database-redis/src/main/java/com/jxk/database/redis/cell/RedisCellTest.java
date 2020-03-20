package com.jxk.database.redis.cell;


import com.jxk.database.redis.pool.JReCell;
import com.jxk.database.redis.pool.RedisPoolUtil4J;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisCellTest {
    /**
     * 原生方法
     */
    @Test
    public void testCell() {
        for (int i = 0; i < 10; i++) {
            try (Jedis jedis = RedisPoolUtil4J.getConnection()) {
                jedis.getClient().sendCommand("cl.throttle"::getBytes, "cellkey".getBytes(), "2".getBytes(), "1".getBytes(), "5".getBytes());
                List<Long> result = jedis.getClient().getIntegerMultiBulkReply();
                Long integer = result.get(0);
                if (integer.intValue() == 0) {
                    System.out.println("执行成功");
                } else {
                    System.out.println("操作过于频繁，请" + (result.get(3) + 1) + "秒后重试");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 封装方法
     */
    @Test
    public void testCell2() throws InterruptedException {
        JReCell cellClient = RedisPoolUtil4J.getCellClient();
        for (int i = 0; i < 20; i++) {
            List<Long> result = cellClient.throttle("cellkey", 5, 1, 2);
            Long integer = result.get(0);
            if (integer.intValue() == 0) {
                System.out.println("执行成功");
            } else {
                System.out.println("操作过于频繁，请" + (result.get(3) + 1) + "秒后重试");
                Thread.sleep(1000);
            }
        }
    }
}
