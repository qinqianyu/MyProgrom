package Test;

import org.junit.Test;

import java.io.File;
import java.util.*;

public class MyTest {

    @Test
    public void test1() {
        List<String> mutations = new ArrayList<String>();
        for (String tmp : mutations
        ) {
            System.out.println("--");
        }
        System.out.println("88");
    }


    @Test
    public void test2() {
        String s = "你好世界";
        System.out.println(s.substring(0, 2));
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        LinkedHashMap<Object, Object> objectObjectLinkedHashMap = new LinkedHashMap<>();
    }

    @Test
    public void test3() {


        File file = new File("C:/Users/24109/Desktop/同步/root/cqgs");

        if (file.exists()) {
            System.out.println("文件已经存在");
        } else {
            // return;
            boolean mkdir = file.mkdirs();
            System.out.println(mkdir);
        }

        if (file.isDirectory()) {
            System.out.println("是文件夹");
        }
        if (file.isFile()) {
            System.out.println("是文件");
        }

    }

    @Test
    public void test4() {
        List<String> columns=new ArrayList<>() ;
        columns.add("a");
        columns.add("b");
        columns.add("c");
        columns.add("d");
        String sqlstart = "select '";
        String sqlsmid1 = "' AS COLUMNS,";
        String sqlsmid2 = "";
        String sqlsend = " FROM " + "space" + "." + "tableName";
        boolean firstFlag = true;
        System.out.println("---------------------");
        System.out.println(columns.size());
        System.out.println("---------------------");
        for (String column : columns) {
            if (firstFlag) {
                sqlstart = sqlstart + column;
                sqlsmid2 = sqlsmid2 + column;
                firstFlag=false;
            } else {
                sqlstart = sqlstart + "|" + column;
                sqlsmid2 = sqlsmid2 + "," + column;
            }
        }
        String sql = sqlstart + sqlsmid1 + sqlsmid2 + sqlsend;
        System.out.println(sql);
    }
}
