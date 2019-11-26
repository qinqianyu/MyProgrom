package redis.Bloom;

import io.rebloom.client.Client;
import org.junit.Test;
import redis.pool.RedisPoolUtil4J;


public class BloomTest2 {
    public static final String BF_Test_KEY = "TestFilter";

    @Test
    public void before() {
        Client client = RedisPoolUtil4J.getClientConnection();
        client.delete(BF_Test_KEY);
        client.createFilter(BF_Test_KEY, 200, 0.001);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(System.currentTimeMillis()%1000 + "即将获取连接----" + i);
            Client clientConnection = RedisPoolUtil4J.getClientConnection();
            System.out.println(System.currentTimeMillis()%1000 + "即将执行命令----" + i);
            clientConnection.exists(BF_Test_KEY,"char"+i);
            System.out.println(System.currentTimeMillis()%1000 + "获取连接完成----" + i);
           /* Jedis connection = RedisPoolUtil4J.getConnection();
            System.out.println(System.currentTimeMillis() + "获取jedis连接完成----" + i + connection);*/
        }
    }
}
