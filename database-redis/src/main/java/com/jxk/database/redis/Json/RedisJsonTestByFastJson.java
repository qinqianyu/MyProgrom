package com.jxk.database.redis.Json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.jxk.database.redis.pool.RedisPoolUtil4J;
import com.redislabs.modules.rejson.JReJSON;
import com.redislabs.modules.rejson.Path;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.jxk.database.redis.keys.redisKeys.jsonKey;

/**
 * @description: 利用fastjson实现String转化bean
 * @author: jxk
 * @create: 2020-03-23 16:21
 **/
public class RedisJsonTestByFastJson {
    private String mykey = jsonKey;

    @Test
    public void test1() {
        JReJSON jsonClient = RedisPoolUtil4J.getJsonClient();
        long timeMillis = System.currentTimeMillis();
        CpuCut cpuCut = CpuCut.builder().time(new String[]{new SimpleDateFormat("HH:mm:ss").format(new Date(timeMillis))}).value(new Integer[]{25}).build();
        jsonClient.set(mykey, cpuCut);

        //拿到數組time
        JSONArray jsonArray = RedisPoolUtil4J.getJsonClient().get(mykey, JSONArray.class, new Path(".time"));
        List<String> times = jsonArray.toJavaList(String.class);
        System.out.println(times);

        // //拿到數組value
        //拿到數組time
        jsonArray = RedisPoolUtil4J.getJsonClient().get(mykey, JSONArray.class, new Path(".value"));
        List<Integer> value = jsonArray.toJavaList(Integer.TYPE);
        System.out.println(value);
    }

    @Test
    public void test2() {
        JReJSON jsonClient = RedisPoolUtil4J.getJsonClient();
        long timeMillis = System.currentTimeMillis();
        CpuCut cpuCut = CpuCut.builder().time(new String[]{new SimpleDateFormat("HH:mm:ss").format(new Date(timeMillis))}).value(new Integer[]{25}).build();
        jsonClient.set(mykey, cpuCut);

        //拿到數組time
        JSONArray jsonArray = RedisPoolUtil4J.getJsonClient().get(mykey, JSONArray.class, new Path(".time"));
        List<String> times = JSON.parseObject(jsonArray.toJSONString(), new TypeReference<ArrayList<String>>(){});
        System.out.println(times);

        // //拿到數組value
        jsonArray = RedisPoolUtil4J.getJsonClient().get(mykey, JSONArray.class, new Path(".value"));
        List<Integer> value = JSON.parseObject(jsonArray.toJSONString(), new TypeReference<ArrayList<Integer>>(){});
        System.out.println(value);
    }
}
