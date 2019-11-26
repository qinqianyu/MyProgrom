package redis.Bloom;

import io.rebloom.client.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.util.Pool;

public class Client2 extends Client {
    public Client2(Pool<Jedis> pool) {
        super(pool);
    }

    public Client2(String host, int port, int timeout, int poolSize) {
        super(host, port, timeout, poolSize);
    }

    public Client2(String host, int port) {
        super(host, port);
    }
}
