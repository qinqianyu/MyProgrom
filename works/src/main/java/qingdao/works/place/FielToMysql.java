package qingdao.works.place;

import java.io.*;
import java.sql.*;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-26 14:46
 **/

public class FielToMysql {
    private static String url = "jdbc:mysql://192.168.20.134:3306/neo4j";
    private static String user = "hive";
    private static String password = "bigdata";

    private static Connection getConn() {
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

    private static String infile = "C:/Users/24109/Desktop/行政区划/行政代码.txt";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(infile));
             Connection connection = getConn()) {
            insert(reader, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param reader 数据库连接
     * @throws SQLException
     */
    public static void insert(BufferedReader reader, Connection conn) throws SQLException {
        String sql = "insert into location (code,name) VALUES(?,?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] lines = line.split("\t");
                pstm.setString(1, lines[0]);
                pstm.setString(2, lines[1]);
                pstm.addBatch();
            }
            pstm.executeBatch();
            conn.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
