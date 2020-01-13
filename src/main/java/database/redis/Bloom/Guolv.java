package database.redis.Bloom;

import org.junit.Test;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Guolv {
    private static final String infileName = "C:/Users/24109/Desktop/青岛/企业名单.txt";
    private static final String outfile1 = "C:/Users/24109/Desktop/青岛/out_1.txt";
    private static final String dropfile1 = "C:/Users/24109/Desktop/青岛/drop_1.txt";
    private static final String dropfile2 = "C:/Users/24109/Desktop/青岛/drop_2.txt";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(infileName));
             BufferedWriter writerout1 = new BufferedWriter(new FileWriter(outfile1));
             BufferedWriter writerdrop1 = new BufferedWriter(new FileWriter(dropfile1));
             BufferedWriter writerdrop2 = new BufferedWriter(new FileWriter(dropfile2));
        ) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if (checkLen(tempString)) {
                    writerdrop1.write(tempString);
                    writerdrop1.newLine();
                } else {
                    tempString=tempString.replace("*","").replace(" ","").replace("。","");
                    writerout1.write(tempString);
                    writerout1.newLine();
                }
            }
        } catch (Exception ignored) {
        }
    }

    private static boolean checkXing(String word) {
        return Pattern.matches(".*\\*{1,}.*", word);
    }

    private static boolean checkLen(String word) {
        return word.replaceAll("[^\u4e00-\u9fa5]", "").length() < 3;
    }

    @Test
    public void Testfun() {
        String str = "javaasxdasd";
        System.out.println(checkLen(str));
    }

    @Test
    public void Testfun2() {
        String str1 = "java判断是否为汉字";
        String str2 = "全为汉字";
        String reg = "[\\u4e00-\\u9fa5]+";
        System.out.println(str1.matches(reg));//false
        System.out.println(str2.matches(reg));//true
    }

    @Test
    public void Testfun3() {
        int count = 0;
        String reg = "[\\u4e00-\\u9fa5]";
        String str = "javaChinese";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        while (m.find()) {
            count = count + 1;
        }
        System.out.println("共有汉字" + count + "个 ");
    }
}