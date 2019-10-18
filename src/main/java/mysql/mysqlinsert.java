package mysql;


import java.sql.*;
import java.util.Random;

public class mysqlinsert {
    private String url = "jdbc:mysql://localhost:3306/neo4j?serverTimezone=UTC&rewriteBatchedStatements=true";
    private String user = "root";
    private String password = "123456";
    private int liangji=100000;

    public static void main(String[] args) {
        new mysqlinsert().Test();
    }

    public void Test() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            //计时开始
            Long startTime = System.currentTimeMillis();
            insertgongsi(conn,pstm);
            insertpeople(conn, pstm);
            insertguanxi(conn, pstm);
            Long endTime = System.currentTimeMillis();
            System.out.println("OK,用时：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    void insertgongsi(Connection conn, PreparedStatement pstm) throws SQLException {
        String randomJianHan;
        String sql = "insert into gongsi (name) VALUES(?)";
        pstm = conn.prepareStatement(sql);
        conn.setAutoCommit(false);
        for (int i = 1; i <= liangji; i++) {
            randomJianHan = "公-" + RandomStringSample.getRandomJianHan(3);
            pstm.setString(1, randomJianHan);
            pstm.addBatch();
        }
        pstm.executeBatch();
        conn.commit();
    }

    void insertpeople(Connection conn, PreparedStatement pstm) throws SQLException {
        String randomJianHan;
        String sql = "insert into people (name) VALUES(?)";
        pstm = conn.prepareStatement(sql);
        conn.setAutoCommit(false);
        for (int i = 1; i <= liangji; i++) {
            randomJianHan = "人-" + RandomStringSample.getRandomJianHan(3);
            pstm.setString(1, randomJianHan);
            pstm.addBatch();
        }
        pstm.executeBatch();
        conn.commit();
    }

    void insertguanxi(Connection conn, PreparedStatement pstm) throws SQLException {
        String sql = "insert into guanxi (start,end,end2,end3,end4) VALUES(?,?,?,?,?)";
        pstm = conn.prepareStatement(sql);
        conn.setAutoCommit(false);
        Random rand = new Random();
        int a, b, c, d;
        for (int i = 1; i <= liangji; i++) {
            a = rand.nextInt(liangji)+1;
            while (a==i){
                a=rand.nextInt(liangji)+1;
            }
            b = rand.nextInt(liangji)+1;
            while (a==b){
                b=rand.nextInt(liangji)+1;
            }
            c = rand.nextInt(liangji)+1;
            d = rand.nextInt(liangji)+1;
            while (d==c){
                d=rand.nextInt(liangji)+1;
            }
            pstm.setInt(1, i);
            pstm.setInt(2, a);
            pstm.setInt(3, b);
            pstm.setInt(4, c);
            pstm.setInt(5, d);
            pstm.addBatch();
        }
        pstm.executeBatch();
        conn.commit();
    }
}