package database.redis.cell;

import database.redis.conectTest.RedisPoolUtil;
import database.redis.pool.JReCell;
import database.redis.pool.RedisPoolUtil4J;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisCellTest {
    @Test
    public void testCell() {
        for (int i = 0; i < 10; i++) {
            try (Jedis jedis = RedisPoolUtil.getPool().getResource()) {
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
