package hanlp.work.view;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class guolv {
    private static final String infileName = "C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\疑似实体.txt";
    //private static final String infileName = "C:\\Users\\24109\\AppData\\Roaming\\feiq\\Recv Files\\mkt_invopt.txt";
    private static final String outfileName1 = "C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\out1.txt";
    private static final String outfileName2 = "C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\out2.txt";
    private static final String outfileName3 = "C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\out3.txt";
    private static final String outfileName4 = "C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\out4.txt";
    private static final String outfileName5 = "C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\out5.txt";
    private static final String outfileName6 = "C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\out6.txt";
    private static HashSet<String> hashSet;
    private static HashSet<String> hashSetV;
    private static List<String> listO;


    static {
        hashSet = new HashSet<>();
        hashSet.add("（");
        hashSet.add("）");
        hashSet.add("(");
        hashSet.add(")");
        hashSetV = new HashSet<>();
        hashSetV.add("vi");
        hashSetV.add("vd");
        hashSetV.add("v");
        listO = new ArrayList<>();
        listO.add("公司");
        listO.add("集团");
        listO.add("医院");
        listO.add("厂");
        listO.add("出版社");
       /* listO.add("中心");
        listO.add("（集团）");
        listO.add("（有限合伙）");
        listO.add("（普通合伙）");*/
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(infileName));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfileName1));
             BufferedWriter writer2 = new BufferedWriter(new FileWriter(outfileName2));
             BufferedWriter writer3 = new BufferedWriter(new FileWriter(outfileName3));
             BufferedWriter writer4 = new BufferedWriter(new FileWriter(outfileName4));
             BufferedWriter writer5 = new BufferedWriter(new FileWriter(outfileName5));
             BufferedWriter writer6 = new BufferedWriter(new FileWriter(outfileName6));
        ) {
            String tempString = null;
            int line = 0;
            while ((tempString = reader.readLine()) != null) {
                line++;
                if (line % 100000 == 0)
                    System.out.println(line);
                if (!checkM(tempString)) {
                    writer1.write(tempString);
                    writer1.newLine();
                    continue;
                }
                if (!checkV(tempString)) {
                    writer2.write(tempString);
                    writer2.newLine();
                    continue;
                }
                if (!checkde(tempString)) {
                    writer3.write(tempString);
                    writer3.newLine();
                    continue;
                }
                if (!checkJ(tempString)) {
                    writer4.write(tempString);
                    writer4.newLine();
                    continue;
                }
                if (!checkO(tempString)) {
                    writer5.write(tempString);
                    writer5.newLine();
                    continue;
                }
                writer6.write(tempString);
                writer6.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static boolean checkW(String word) {
        List<Term> list = HanLP.segment(word);
        for (Term term : list) {
            if (term.nature.toString().equals("w") && !hashSet.contains(term.word)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkOrgPre(String word) {
        int wordLengthLimit = 8;
        if (word.length() < wordLengthLimit) {
            return false;
        }
        List<Term> list = HanLP.segment(word);
        for (Term term : list) {
            if (term.nature.toString().equals("ns")) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkM(String word) {
        if (Pattern.matches(".*[0-9]{1,}.*", word)) {
            return false;
        }
        return true;
    }

    private static boolean checkV(String word) {
        List<Term> list = HanLP.segment(word);
        for (Term term : list) {
            if (hashSetV.contains(term.nature.toString())) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkJ(String word) {
        List<Term> list = HanLP.segment(word);
        for (Term term : list) {
            if (term.nature.toString().equals("p")) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkde(String word) {
        if (word.contains("的"))
            return false;
        return true;
    }

    private static boolean checkO(String word) {
        for (String O : listO) {
            if (Pattern.matches(".*" + O + "$", word)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        System.out.println(checkW("湖南和@中核集团"));
    }
}
