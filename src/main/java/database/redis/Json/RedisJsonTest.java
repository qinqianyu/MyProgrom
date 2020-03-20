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
    private String mykey = "myjson";

    @Test
    public void test1() {
        JReJSON jsonClient = RedisPoolUtil4J.getJsonClient();
        long timeMillis ;
        String timestr;
        for (int i = 0; i < 5; i++) {
            timeMillis=System.currentTimeMillis();
            timestr=new SimpleDateFormat("HH:mm:ss").format(new Date(timeMillis));
            //jsonClient.set();
        }
         timeMillis = System.currentTimeMillis();
        CpuCut build = CpuCut.builder().time(new String[]{new SimpleDateFormat("HH:mm:ss").format(new Date(timeMillis))}).value(new Integer[]{25}).build();
        String jsonString = JSON.toJSONString(build);
        jsonClient.set(mykey, build);
        System.out.println(jsonString);
        String s = jsonClient.get(mykey, String.class, new Path(".time"));
        Integer v = jsonClient.get(mykey, Integer.class, new Path(".value"));
        System.out.println(s);
        System.out.println(v);
    }
    @Test
    public void test2() {
        String tmp="{\n\t\"time\": [\t],\n\t\"value\": [\n\t\t1,\n\t\t2\n\t]\n}";
        System.out.println(tmp);
    }
}
