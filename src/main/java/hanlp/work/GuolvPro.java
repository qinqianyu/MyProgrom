package hanlp.work;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.io.*;
import java.util.List;

public class GuolvPro {

    private static final String infileName = "C:\\Users\\24109\\AppData\\Roaming\\feiq\\Recv Files\\mkt_invopt.txt";
    private static final String outfileName = "C:\\Users\\24109\\Desktop\\机构识别\\机构数据\\mkt_invopt2.txt";
    private static final String outfileName2 = "C:\\Users\\24109\\Desktop\\机构识别\\机构数据\\mkt_invopt3.txt";
    private static final String outfileName3 = "C:\\Users\\24109\\Desktop\\机构识别\\机构数据\\mkt_invopt4.txt";

    public static void main(String[] args) {
        File infile = new File(infileName);
        File outfile = new File(outfileName);
        File outfile2 = new File(outfileName2);
        File outfile3 = new File(outfileName3);
        try (BufferedReader reader = new BufferedReader(new FileReader(infile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outfile));
             BufferedWriter writer2 = new BufferedWriter(new FileWriter(outfile2));
             BufferedWriter writer3 = new BufferedWriter(new FileWriter(outfile3))
        ) {
            String tempString = null;
            long line = 0;
            long line2 = 0;
            while ((tempString = reader.readLine()) != null) {
                line++;
                if (tempString.split(" ").length > 1) {
                    writer3.write(tempString);
                    writer3.newLine();
                    continue;
                }
                if (line % (100 * 10000) == 0) {
                    System.out.println("------------已经读取" + line + "行");
                    System.out.println("------------已经写入" + line2 + "行");
                }
                boolean analyse = new MarketPlayerIncrease().analyse(tempString);
                if (analyse) {
                    writer.write(tempString);
                    writer.newLine();
                    line2++;
                    continue;
                }
                //writer2.write(HanLP.newSegment().enableOrganizationRecognize(true).seg(tempString).toString());
                writer2.write(tempString);
                writer2.newLine();
            }
            System.out.println("------------已经读取" + line + "行");
            System.out.println("------------已经写入" + line2 + "行");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
