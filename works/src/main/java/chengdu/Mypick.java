package chengdu;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Stream;

public class Mypick {

    /**
     * 根据接口的调用结果获取有问题的数据项的索引。
     * index_01.txt是调用结果似于日志的一部分记录了每次调用的结果，成功是Your test name：true,失败是Your test name：false
     */
    @Test
    public void test1() {
        String inFile = "F:\\工作文件\\导入健康吗\\获取索引\\index_01.txt";
        String outfile = "F:\\工作文件\\导入健康吗\\获取索引\\index_02.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outfile))
        ) {
            String tempString;
            int index = 0;
            while ((tempString = reader.readLine()) != null) {
                //只读取有意义的行
                if (tempString.contains("Your test name")) {
                    index++;
                    //只记录失败的数据的行标
                    if (tempString.contains("false")) {
                        System.out.println(index);
                        writer.write(index + ",");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据数据索引，把对应的出错行从原始数据集中挑选出来，增加索引列，同时替换分隔符
     */

    @Test
    public void test2() {
        String inFile = "F:\\工作文件\\导入健康吗\\hashCode";
        String outfile = "F:\\工作文件\\导入健康吗\\hashCode.error";
        //索引数据集从index_02.txt文件复制粘贴而来
        Integer[] indexs = {277, 339, 2078, 2079, 2580, 2651, 2860, 3400, 3638, 3796, 4317, 4361, 4420, 4567, 5349, 5447, 5912,
                6003, 6851, 7093, 7650, 7779, 8486, 8800, 9668, 10313, 10501, 10985, 10989, 11015, 11142, 11185, 11640};
        System.out.println(indexs.length);
        HashSet hashSet = new HashSet<>(Arrays.asList(indexs));
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outfile))
        ) {
            writer.write("index,userCode,personName");
            writer.newLine();
            String tempString;
            int index = 0;
            while ((tempString = reader.readLine()) != null) {
                index++;
                if (hashSet.contains(index)) {
                    tempString = tempString.replace("][", ",");
                    tempString = index + "," + tempString;
                    writer.write(tempString);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *调用es接口查询姓名，再计算健康码
     */
    @Test
    public void test3() {
        String inFile = "F:\\工作文件\\导入健康吗\\修改姓名\\hashCode.replace";
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile))) {
            reader.readLine();
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                String[] split = tempString.split(",");
                if (!insert(split[1], getname(split[1]))) {
                    //插入失败则打印数据具体内容
                    System.out.println(tempString);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将出错的数据集增加一列，esName列，内容为在es中的姓名。
     */
    @Test
    public void test4() {
        String inFile = "F:\\工作文件\\导入健康吗\\对比\\hashCode.error";
        String outfile = "F:\\工作文件\\导入健康吗\\对比\\hashCode.error.replace";
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outfile))) {
            reader.readLine();
            writer.write("index][userCode][personName][esName");
            writer.newLine();
            String tempString;
            String[] split;
            while ((tempString = reader.readLine()) != null) {
                split = tempString.split(",");
                tempString=tempString+","+getname(split[1]);
                split = tempString.split(",");
                String reduce = Stream.of(split).reduce((x, y) -> x + "][" + y).get();
                writer.write(reduce);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param code
     * @return 返回es中的的姓名
     */
    public static String getname(String code) {
        String url = "http://172.24.146.5:9200/basic/person/" + code + "?pretty";
        String s = sendGet(url);
        JSONObject jsonObject = JSON.parseObject(s);
        JSONObject source = jsonObject.getJSONObject("_source");
        String name = source.getString("personName");
        return name;
    }

    /**
     * 调用api计算健康码插入es
     * @param code
     * @param name
     * @return
     * @throws UnsupportedEncodingException
     */
    public static boolean insert(String code, String name) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(name, "utf-8");
        encode = encode.replace("%3D",  "=");
        encode = encode.replace("%2F", "/");
        encode = encode.replace("+", "%20");
        encode = encode.replace("%26", "&");
        String url = "http://172.24.146.24:13800/api/v1/person/health/code?userCode=" + code + "&personName=" + encode + "&languagesCode=04";
        String s = sendGet(url);
        JSONObject jsonObject = JSON.parseObject(s);
        String msg = jsonObject.getString("msg");
        return msg.equals("请求成功");
    }

    /**
     * 发送request请求，获取字符串结果
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


}
