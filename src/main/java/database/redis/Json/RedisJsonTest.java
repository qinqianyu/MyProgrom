package database.redis.Json;

import com.alibaba.fastjson.JSON;
import com.redislabs.modules.rejson.JReJSON;
import com.redislabs.modules.rejson.Path;
import database.redis.pool.RedisPoolUtil4J;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RedisJsonTest {
    private String mykey="myjson";
    @Test
    public void test1(){
        JReJSON jsonClient = RedisPoolUtil4J.getJsonClient();
        long timeMillis = System.currentTimeMillis();
        CpuCut build = CpuCut.builder().time(new SimpleDateFormat("HH:mm:ss").format(new Date(timeMillis))).value(25).build();
        String jsonString = JSON.toJSONString(build);
        jsonClient.set(mykey,jsonString);
        String s = jsonClient.get(mykey, String.class, new Path(".time"));
        System.out.println(s);
    }

}
