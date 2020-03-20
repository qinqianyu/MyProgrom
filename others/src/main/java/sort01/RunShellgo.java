package sort01;

import com.jcraft.jsch.JSchException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RunShellgo {
    private static String url = "jdbc:mysql://192.168.20.143:3306/data_import";
    private static String user = "root";
    private static String password = "admin123!@#";

    private static String[] filelist = {"aa", "bb", "cc"};
    private static String fileRoot = "/root/jxk/";

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

    public static void main(String[] args) throws SQLException, JSchException {
        Connection conn = getConn();
        PreparedStatement preparedStatement = conn.prepareStatement("select table_name from information_schema.tables where table_schema=?");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入表空间名称");
        String tablespace = scanner.nextLine();
        preparedStatement.setString(1, tablespace);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<String> tableList = new ArrayList<>();
        String tablespaceFile = fileRoot + tablespace;
        JSchExecutor root139 = new JSchExecutor("root", "123456", "192.168.20.139");
        root139.connect();
        root139.createDir(tablespaceFile);
        String tablename;
        while (resultSet.next()) {
            tablename = resultSet.getString(0);
            root139.createDir(tablespaceFile + "/" + tablename);
        }

    }


    @Test
    public void jxktest() throws JSchException {
        JSchExecutor root139 = new JSchExecutor("root", "123456", "192.168.20.139");
        root139.connect();
        root139.createDir(fileRoot + "neo4j");
        root139.disconnect();
    }
}
