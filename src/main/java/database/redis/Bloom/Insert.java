package database.redis.Bloom;

import database.redis.pool.RedisPoolUtil4J;
import io.rebloom.client.Client;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class Insert {
    private static final int DEFAULT_SIZE = 2 << 28;

    private static final String key = "redis:bloom:filter";

    private static final int[] seeds = new int[]{5, 7, 11, 13, 31, 37, 61};

    private final String fileName = "C:\\Users\\24109\\Desktop\\青岛\\out_1.txt";


    public void initMkt() {
        log.info("初始化redis主体哈希信息");
        File file = new File(fileName);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            long line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                line++;
                if (line >= 175 * 10000) {
                    break;
                }
            }
            while ((tempString = reader.readLine()) != null) {
                add(tempString);
                line++;
                if (line % 10000 == 0) {
                    System.out.println(line + "--" + System.currentTimeMillis() + " -- 创建bitmap:--->" + tempString);
                }
            }
            reader.close();
            log.info("初始化主体{}条", line);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    @Test
    public void TrstFL() {
        System.out.println(contains("青岛海基正心医药连锁有限公司十六店"));
        //add("南京莱斯");
        System.out.println(contains("青岛海基正心医药连锁有限公司十六店大"));
    }

    @Test
    public void Trstpool() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Jedis connection = RedisPoolUtil4J.getConnection();
            final Boolean getbit = connection.getbit(key, 55);
            connection.close();
        }
        System.out.println(10000 / (System.currentTimeMillis() - start));
    }

    @Test
    public void Chushihua() {
        initMkt();
    }

    public void add(String value) throws InterruptedException {
        insert(value, 5);
    }

    private static void insert(String value, int times) throws RuntimeException, InterruptedException {
        for (int i = 0; i < times; i++) {
            try {
                Client bloomClient = RedisPoolUtil4J.getBloomClient();
                bloomClient.add(key, value);
                return;
            } catch (Exception e) {
                System.out.println("失败第" + (i + 1) + "次" + System.currentTimeMillis() + "--" + e.getMessage());
                Thread.sleep(60000);
            }
        }
        throw new RuntimeException();
    }

    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        Client bloomClient = RedisPoolUtil4J.getBloomClient();
        return bloomClient.exists(key, value);
    }
}
