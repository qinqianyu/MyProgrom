package mysql;


import java.sql.*;
import java.util.Random;

public class mysqlinsert {
    private String url = "jdbc:mysql://localhost:3306/shujuku";
    private String user = "root";
    private String password = "123456";
        public void Test() {
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rt = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);
                String sql = "INSERT INTO userinfo(uid,uname,uphone,uaddress) VALUES(?,CONCAT('姓名',?),?,?)";
                pstm = conn.prepareStatement(sql);
                Long startTime = System.currentTimeMillis();
                Random rand = new Random();
                int a, b, c, d;
                for (int i = 1; i <= 1000; i++) {
                    pstm.setInt(1, i);
                    pstm.setInt(2, i);
                    a = rand.nextInt(10);
                    b = rand.nextInt(10);
                    c = rand.nextInt(10);
                    d = rand.nextInt(10);
                    pstm.setString(3, "188" + a + "88" + b + c + "66" + d);
                    pstm.setString(4, "xxxxxxxxxx_" + "188" + a + "88" + b + c + "66" + d);
                    pstm.executeUpdate();
                }
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


}