package qingdao.works.domain;

import org.junit.Test;
import qingdao.untils.RedisPoolUtil4J;
import redis.clients.jedis.Jedis;

import java.sql.*;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-27 14:25
 **/
public class FileToRedis {
    private static String url = "jdbc:mysql://192.168.20.148:3306/les_crawlerplatform_server";
    private static String user = "root";
    private static String password = "les-crawler";

    public static Connection getConn() {
        Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Test
    public void test() throws SQLException {
        try (Connection conn = getConn();
             Jedis jedis = RedisPoolUtil4J.getConnection()) {
            String sql = "select name,domain from les_crawlerplatform_server.les_url_manage";
            PreparedStatement statement = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // System.out.println(resultSet.getString(2));
                jedis.hset("hash_domain_name", resultSet.getString(2), resultSet.getString(1));
            }
        }
    }

    @Test
    public void test1() {
        try (Jedis jedis = RedisPoolUtil4J.getConnection()) {
            final String hash_domain_name = jedis.hget("hash_domain_name", "ijjnews.com");
            System.out.println(hash_domain_name);
        }
    }


}
