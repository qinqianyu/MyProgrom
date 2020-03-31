package qingdao.works.marketplaer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 用来过滤企业名称不可使用的企业
 */
public class EnterpriseFilter {
    private static final String infileName = "C:\\Users\\24109\\Desktop\\青岛\\企业名单.txt";
    private static final String outfile1 = "C:/Users/24109/Desktop/青岛/out_1.txt";
    private static final String dropfile1 = "C:/Users/24109/Desktop/青岛/drop_1.txt";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(infileName));
             BufferedWriter writerout1 = new BufferedWriter(new FileWriter(outfile1));
             BufferedWriter writerdrop1 = new BufferedWriter(new FileWriter(dropfile1))
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

    /**
     *
     * @param word 被检查的字符串
     * @return 汉字数量小于3
     */
    private static boolean checkLen(String word) {
        return word.replaceAll("[^\u4e00-\u9fa5]", "").length() < 3;
    }
}