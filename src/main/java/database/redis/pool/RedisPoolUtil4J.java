package database.redis.pool;

import io.rebloom.client.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: leisurexi
 * Date: 2019-10-08
 * Time: 9:02 下午
 */
public class RedisPoolUtil4J {

    private static final JedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(5);//连接池总数
        config.setMinIdle(1);//最少空闲连接数
        config.setMaxIdle(5);//最大空先连接数
        pool = new JedisPool(config, "192.168.20.138");
    }

    public static Jedis getConnection() {
        return pool.getResource();
    }

    public static Client getClientConnection() {
        return new Client(pool);
    }

    public static void execute(CallWithJedis caller) {
        Jedis jedis = pool.getResource();
        try {
            caller.call(jedis);
        } catch (JedisConnectionException e) {
            caller.call(jedis);
        } finally {
            jedis.close();
        }
    }

}

@FunctionalInterface
interface CallWithJedis {

    void call(Jedis jedis);

}
