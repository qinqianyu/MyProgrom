package hanlp.analysis;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.model.perceptron.PerceptronSegmenter;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import database.mysql.pool.MysqlPoolUtil;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class guolv {
    private static final String infileName = "C:\\Users\\24109\\AppData\\Roaming\\feiq\\Recv Files\\mkt_invopt.txt";
    private static final String outfile1 = "C:\\Users\\24109\\Desktop\\机构识别2\\out1.txt";
    private static List<String> listO;
    private static String fileRoot;

    static {
        try (InputStream resourceAsStream = AnalysisTest.class.getClassLoader().getResourceAsStream("hanlp.properties");) {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            fileRoot = properties.getProperty("root") + "/";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        listO = new ArrayList<>();//4118
        listO.add("学");//3721
        listO.add("管理局");//3585
        listO.add("交警大队");//3571
        listO.add("局");//3451
        listO.add("法院");//3314
        listO.add("协会");//3099
        listO.add("派出所");//3051
        listO.add("学院");//2822
        listO.add("办公厅");//2811
        listO.add("警察支队");//2804
        listO.add("大使馆");//2789
        listO.add("党");//2789
        listO.add("委员会");//2789
        listO.add("财政部");//2789
        listO.add("外交部");//2588
        listO.add("组织部");//2584
        listO.add("检察院");//2567
    }

    public static void main(String[] args) {
        Segment segment = HanLP.newSegment("perceptron").enableCustomDictionary(false);
        try (BufferedReader reader = new BufferedReader(new FileReader(infileName));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter("C:\\Users\\24109\\Desktop\\机构识别2\\perceptron_out.txt"));
             BufferedWriter writer2 = new BufferedWriter(new FileWriter("C:\\Users\\24109\\Desktop\\机构识别2\\perceptron_drop.txt"));
        ) {
            String tempString;
            int count = 0;
            boolean flag;
            while ((tempString = reader.readLine()) != null) {
                if ((count & (int) (Math.pow(2, 17) - 1)) == 0)
                    System.out.println(count + "-->" + System.currentTimeMillis());
                count++;
                List<Term> termList = segment.seg(tempString);
                flag = true;
                for (Term tmp : termList) {
                    if (tmp.nature.toString().equals("nt")) {
                        writer1.write(tmp.word);
                        writer1.newLine();
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    writer2.write(tempString);
                    writer2.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void main2() {
        Segment segment = HanLP.newSegment("crf").enableCustomDictionary(false);
        try (BufferedReader reader = new BufferedReader(new FileReader(infileName));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter("C:\\Users\\24109\\Desktop\\机构识别2\\crf_out.txt"));
             BufferedWriter writer2 = new BufferedWriter(new FileWriter("C:\\Users\\24109\\Desktop\\机构识别2\\crf_drop.txt"));
        ) {
            String tempString;
            int count = 0;
            boolean flag;
            while ((tempString = reader.readLine()) != null) {
                if ((count & (int) (Math.pow(2, 17) - 1)) == 0)
                    System.out.println(count + "-->" + System.currentTimeMillis());
                count++;
                List<Term> termList = segment.seg(tempString);
                flag = true;
                for (Term tmp : termList) {
                    if (tmp.nature.toString().equals("nt")) {
                        writer1.write(tmp.word);
                        writer1.newLine();
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    writer2.write(tempString);
                    writer2.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        Segment segment = HanLP.newSegment("crf").enableCustomDictionary(false);
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\疑似实体.txt"));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter("C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\疑似实体out.txt"))) {
            String tempString;
            int count = 0;
            while ((tempString = reader.readLine()) != null) {
                //if ((count & (int)(Math.pow(2,17)-1) )== 0) System.out.println(count+"-->"+System.currentTimeMillis());
                count++;
                List<Term> termList = segment.seg(tempString);
                for (Term tmp : termList) {
                    if (tmp.nature.toString().equals("nt")) {
                        writer1.write(tmp.word);
                        writer1.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        Segment segment = HanLP.newSegment("perceptron").enableCustomDictionary(false);
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\疑似实体out.txt"));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter("C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\疑似实体out1.txt"))) {
            String tempString;
            int count = 0;
            while ((tempString = reader.readLine()) != null) {
                //if ((count & (int)(Math.pow(2,17)-1) )== 0) System.out.println(count+"-->"+System.currentTimeMillis());
                count++;
                List<Term> termList = segment.seg(tempString);
                for (Term tmp : termList) {
                    if (tmp.nature.toString().equals("nt")) {
                        writer1.write(tempString);
                        writer1.newLine();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\疑似实体out1.txt"));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter("C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\疑似实体out2.txt"))) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                //if ((count & (int)(Math.pow(2,17)-1) )== 0) System.out.println(count+"-->"+System.currentTimeMillis());
                if (checkO(tempString)) {
                    writer1.write(tempString);
                    writer1.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkO(String word) {
        for (String O : listO) {
            if (Pattern.matches(".*" + O + "$", word)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void getend() throws IOException {
        PerceptronSegmenter perceptronSegmenter = new PerceptronSegmenter(fileRoot + "data/model/perceptron/large/cws.bin");
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\疑似实体out1.txt"));
             Connection connection = MysqlPoolUtil.getConnection();
        ) {
            PreparedStatement preparedStatement;
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                //if ((count & (int)(Math.pow(2,17)-1) )== 0) System.out.println(count+"-->"+System.currentTimeMillis());
                List<String> stringList = perceptronSegmenter.segment(tempString);
                 preparedStatement = connection.prepareStatement("INSERT INTO marketplayerEndWord ( word, count ) VALUES ( ?, 1 ) ON DUPLICATE KEY UPDATE count = count +1");
                preparedStatement.setString(1,stringList.get(stringList.size()-1));
                preparedStatement.execute();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


}
