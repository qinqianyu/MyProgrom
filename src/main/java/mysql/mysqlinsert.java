package mysql;


import java.sql.*;
import java.util.Random;

public class mysqlinsert {
    private String url = "jdbc:mysql://localhost:3306/shujuku?serverTimezone=UTC&rewriteBatchedStatements=true";
    private String user = "root";
    private String password = "123456";

    public static void main(String[] args) {
        new mysqlinsert().Test();
    }
    public void Test(){
        Connection conn = null;
        PreparedStatement pstm =null;
        ResultSet rt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String randomJianHan;
            String sql = "insert into shiti (name,type) VALUES(?,?)";
            pstm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            //计时开始
            Long startTime = System.currentTimeMillis();
            Random rand = new Random();
            String a;
            for (int i = 1; i <= 1000; i++) {
                randomJianHan = RandomStringSample.getRandomJianHan(3);
                pstm.setString(1, randomJianHan);
                a = String.valueOf(rand.nextInt(2));
                pstm.setString(2, a);
                pstm.addBatch();
            }
            pstm.executeBatch();
            conn.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("OK,用时：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            if(pstm!=null){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }


}