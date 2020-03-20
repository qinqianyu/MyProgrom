package hanlp.analysis;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import database.redis.pool.RedisPoolUtil4J;
import io.rebloom.client.Client;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-13 16:40
 **/
public class qiye {
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
    @Test
    public void test2() {
        String str = "宁波开晟投资有限公司";
        Segment segmentper = HanLP.newSegment("perceptron").enableCustomDictionary(false);
        Segment segmentcrf = HanLP.newSegment("crf").enableCustomDictionary(false);
        Segment segmento = HanLP.newSegment().enableOrganizationRecognize(true);
        List<Term> seg1 = segmentcrf.seg(str);
        List<Term> seg2 = segmentper.seg(str);
        System.out.println(seg1 + "--" + seg2);
    }

    @Test
    public void test3() {
        Set<String> analyseplacesPer = new HashSet<>(Arrays.asList("a","b","c"));
        Set<String> analyseplacesCrf = new HashSet<>(Arrays.asList("d","b","e"));
        analyseplacesPer.retainAll(analyseplacesCrf);
        System.out.println(analyseplacesPer);
    }
    @Test
    public void test4() {
        Calendar calendar = Calendar.getInstance();
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        calendar.setTime(new Date());
        Date zero = calendar.getTime();
        System.out.println(zero);
    }

    @Test
    public void test5() {
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
