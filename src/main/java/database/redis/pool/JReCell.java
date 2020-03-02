package database.redis.pool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.commands.ProtocolCommand;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.util.Pool;
import redis.clients.jedis.util.SafeEncoder;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static database.redis.pool.RedisPoolUtil4J.getConnection;

public class JReCell {
    private enum Command implements ProtocolCommand {
        throttle("cl.throttle");
        private final byte[] raw;

        Command(String alt) {
            raw = SafeEncoder.encode(alt);
        }

        public byte[] getRaw() {
            return raw;
        }
    }

    private Pool<Jedis> client;

    public JReCell(Pool<Jedis> jedis) {
        this.client = jedis;
    }

    public List<Long> throttle(String key, Integer max_burst, Integer count_per_period, Integer period) {
        List<Long> result;
        try (Jedis conn = getConnection()) {
            conn.getClient()
                    .sendCommand(Command.throttle, encode(key),
                            max_burst.toString().getBytes(),
                            count_per_period.toString().getBytes(),
                            period.toString().getBytes());
            result = conn.getClient().getIntegerMultiBulkReply();
        }
        return result;
    }

    public static byte[] encode(final String str) {
        try {
            if (str == null) {
                throw new JedisDataException("value sent to redis cannot be null");
            }
            return str.getBytes(Protocol.CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new JedisException(e);
        }
    }
}
