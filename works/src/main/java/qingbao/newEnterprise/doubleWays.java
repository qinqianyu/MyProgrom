package qingbao.newEnterprise;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import io.rebloom.client.Client;
import org.junit.Test;
import qingdao.untils.RedisPoolUtil4J;

import java.io.*;
import java.util.*;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-24 10:55
 **/
public class doubleWays {
    /**
     * 回溯分词通过两种分词方法过滤
     */
    @Test
    public void test1() {
        String inFile = "C:\\Users\\24109\\Desktop\\企业分析\\正式\\常规分词.txt";
        String outfile1 = "C:\\Users\\24109\\Desktop\\企业分析\\正式\\常规分词lv.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            Segment segmentper = HanLP.newSegment("perceptron").enableCustomDictionary(false);
            Segment segmentcrf = HanLP.newSegment("crf").enableCustomDictionary(false);
            while ((tempString = reader.readLine()) != null) {
                List<Term> termList = segmentper.seg(tempString);
                List<Term> termList2 = segmentcrf.seg(tempString);
                if (termList.size() == 1 && termList.get(0).nature.toString().equals("nt")) {
                    writer1.write(tempString);
                    writer1.newLine();
                } else if (termList2.size() == 1 && termList2.get(0).nature.toString().equals("nt")) {
                    writer1.write(tempString);
                    writer1.newLine();
                }else {
                    System.out.println(tempString);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重叠分词加布隆过滤
     */

    @Test
    public void test2() {
        String inFile = "C:\\Users\\24109\\Desktop\\企业分析\\正式\\2way分词\\重叠分词.txt";
        String outfile1 = "C:\\Users\\24109\\Desktop\\企业分析\\正式\\2way分词\\重叠分词lv.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            Client bloomClient = RedisPoolUtil4J.getBloomClient();
            while ((tempString = reader.readLine()) != null) {
                if(bloomClient.exists("bl_enterprise",tempString)){
                    writer1.write(tempString);
                    writer1.newLine();
                }
                else {
                    System.out.println(tempString);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
