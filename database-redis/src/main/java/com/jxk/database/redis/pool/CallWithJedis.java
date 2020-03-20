package com.jxk.database.redis.pool;

import redis.clients.jedis.Jedis;

@FunctionalInterface
public interface CallWithJedis {
    void call(Jedis jedis);
}