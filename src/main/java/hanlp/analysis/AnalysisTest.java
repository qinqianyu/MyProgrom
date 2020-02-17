package hanlp.analysis;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.model.perceptron.PerceptronLexicalAnalyzer;
import com.hankcs.hanlp.model.perceptron.PerceptronSegmenter;
import com.hankcs.hanlp.model.perceptron.model.LinearModel;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import javafx.scene.shape.HLineTo;
import org.junit.Test;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import static org.apache.logging.log4j.core.util.Loader.getClassLoader;

public class AnalysisTest {
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

    private static final String inFile = "C:/Users/24109/Desktop/机构识别/kafka疑似/out6.txt";
    private static final String outfile1 = "C:/Users/24109/Desktop/机构识别2/out1.txt";

    @Test
    public void all() {
        fun0();
        fun01();
        fun1();
        fun2();
        fun3();
        fun4();
        fun5();
        fun6();
        fun7();
        fun8();
        fun9();
        fun10();
    }

    @Test
    public void fun0() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            Segment segment = HanLP.newSegment().enableCustomDictionary(false).enableOrganizationRecognize(true);

            int line = 0, count = 0;
            long start = System.currentTimeMillis();
            while ((tempString = reader.readLine()) != null && line < 1000) {
                line++;
                List<Term> termList = segment.seg(tempString);
                if (termList.size() == 1 && termList.get(0).nature.toString().equals("nt"))
                    count++;
                // System.out.println(termList);
            }
            System.out.println("count:" + count + ",耗时:" + (System.currentTimeMillis() - start) + "-->默认--禁词典--识别企");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun01() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            Segment segment = HanLP.newSegment().enableOrganizationRecognize(true);

            int line = 0, count = 0;
            long start = System.currentTimeMillis();
            while ((tempString = reader.readLine()) != null && line < 1000) {
                line++;
                List<Term> termList = segment.seg(tempString);
                if (termList.size() == 1 && termList.get(0).nature.toString().equals("nt"))
                    count++;
                // System.out.println(termList);
            }
            System.out.println("count:" + count + ",耗时:" + (System.currentTimeMillis() - start) + "-->默认--开词典--识别企");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun1() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            Segment segment = HanLP.newSegment("crf").enableCustomDictionary(false).enableOrganizationRecognize(true);

            int line = 0, count = 0;
            long start = System.currentTimeMillis();
            while ((tempString = reader.readLine()) != null && line < 1000) {
                line++;
                List<Term> termList = segment.seg(tempString);
                if (termList.size() == 1 && termList.get(0).nature.toString().equals("nt"))
                    count++;
                // System.out.println(termList);
            }
            System.out.println("count:" + count + ",耗时:" + (System.currentTimeMillis() - start) + "-->crf--禁词典--识别企");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun2() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            Segment segment = HanLP.newSegment("crf").enableCustomDictionary(false);

            int line = 0, count = 0;
            long start = System.currentTimeMillis();
            while ((tempString = reader.readLine()) != null && line < 1000) {
                line++;
                List<Term> termList = segment.seg(tempString);
                if (termList.size() == 1 && termList.get(0).nature.toString().equals("nt"))
                    count++;
                //System.out.println(termList);
            }
            System.out.println("count:" + count + ",耗时:" + (System.currentTimeMillis() - start) + "-->crf--禁词典--不识别企");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun3() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            Segment segment = HanLP.newSegment("perceptron").enableCustomDictionary(false);

            int line = 0, count = 0;
            long start = System.currentTimeMillis();
            while ((tempString = reader.readLine()) != null && line < 1000) {
                line++;
                List<Term> termList = segment.seg(tempString);
                if (termList.size() == 1 && termList.get(0).nature.toString().equals("nt"))
                    count++;
                //System.out.println(termList);
            }
            System.out.println("count:" + count + ",耗时:" + (System.currentTimeMillis() - start) + "-->perceptron--禁词典--不识别企");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun4() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            Segment segment = HanLP.newSegment("perceptron").enableCustomDictionary(false).enableOrganizationRecognize(true);

            int line = 0, count = 0;
            long start = System.currentTimeMillis();
            while ((tempString = reader.readLine()) != null && line < 1000) {
                line++;
                List<Term> termList = segment.seg(tempString);
                if (termList.size() == 1 && termList.get(0).nature.toString().equals("nt"))
                    count++;
                //System.out.println(termList);
            }
            System.out.println("count:" + count + ",耗时:" + (System.currentTimeMillis() - start) + "-->perceptron--禁词典--识别企");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun5() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            PerceptronLexicalAnalyzer segment = new PerceptronLexicalAnalyzer(fileRoot + "data/model/perceptron/pku199801/cws.bin",
                    HanLP.Config.PerceptronPOSModelPath,
                    HanLP.Config.PerceptronNERModelPath);
            int line = 0, count = 0;
            long start = System.currentTimeMillis();
            while ((tempString = reader.readLine()) != null && line < 1000) {
                line++;
                List<Term> termList = segment.seg(tempString);
                if (termList.size() == 1 && termList.get(0).nature.toString().equals("nt"))
                    count++;
                //System.out.println(termList);
            }
            System.out.println("count:" + count + ",耗时:" + (System.currentTimeMillis() - start) + "-->perceptron--开词典--识别企--pku");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun6() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            PerceptronLexicalAnalyzer segment = new PerceptronLexicalAnalyzer("C:/programs/MyProgrom/src/main/java/hanlp/model/msr/cws.bin",
                    HanLP.Config.PerceptronPOSModelPath,
                    HanLP.Config.PerceptronNERModelPath);
            int line = 0, count = 0;
            long start = System.currentTimeMillis();
            while ((tempString = reader.readLine()) != null && line < 1000) {
                line++;
                List<Term> termList = segment.seg(tempString);
                if (termList.size() == 1 && termList.get(0).nature.toString().equals("nt"))
                    count++;
                //System.out.println(termList);
            }
            System.out.println("count:" + count + ",耗时:" + (System.currentTimeMillis() - start) + "-->perceptron--开词典--识别企--msr");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun7() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            PerceptronLexicalAnalyzer segment = new PerceptronLexicalAnalyzer(fileRoot + "data/model/perceptron/large/cws.bin",
                    HanLP.Config.PerceptronPOSModelPath,
                    HanLP.Config.PerceptronNERModelPath);
            int line = 0, count = 0;
            long start = System.currentTimeMillis();
            while ((tempString = reader.readLine()) != null && line < 1000) {
                line++;
                List<Term> termList = segment.seg(tempString);
                if (termList.size() == 1 && termList.get(0).nature.toString().equals("nt"))
                    count++;
                // System.out.println(termList);
            }
            System.out.println("count:" + count + ",耗时:" + (System.currentTimeMillis() - start) + "-->perceptron--开词典--识别企--large");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun8() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            PerceptronLexicalAnalyzer segment = (PerceptronLexicalAnalyzer) new PerceptronLexicalAnalyzer(fileRoot + "data/model/perceptron/large/cws.bin",
                    HanLP.Config.PerceptronPOSModelPath,
                    HanLP.Config.PerceptronNERModelPath).enableCustomDictionary(false);
            int line = 0, count = 0;
            long start = System.currentTimeMillis();
            while ((tempString = reader.readLine()) != null && line < 1000) {
                line++;
                List<Term> termList = segment.seg(tempString);
                if (termList.size() == 1 && termList.get(0).nature.toString().equals("nt"))
                    count++;
            }
            System.out.println("count:" + count + ",耗时:" + (System.currentTimeMillis() - start) + "-->perceptron--禁词典--识别企--large");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void fun9() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            Segment segment = HanLP.newSegment("nshort").enableCustomDictionary(false).enableOrganizationRecognize(true);
            int line = 0, count = 0;
            long start = System.currentTimeMillis();
            while ((tempString = reader.readLine()) != null && line < 1000) {
                line++;
                List<Term> termList = segment.seg(tempString);
                if (termList.size() == 1 && termList.get(0).nature.toString().equals("nt"))
                    count++;
              //  System.out.println(termList);
            }
            System.out.println("count:" + count + ",耗时:" + (System.currentTimeMillis() - start) + "-->nshort--禁词典--识别企--large");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void fun10() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            Segment segment = HanLP.newSegment("nshort").enableOrganizationRecognize(true);
            int line = 0, count = 0;
            long start = System.currentTimeMillis();
            while ((tempString = reader.readLine()) != null && line < 1000) {
                line++;
                List<Term> termList = segment.seg(tempString);
                if (termList.size() == 1 && termList.get(0).nature.toString().equals("nt"))
                    count++;
              //  System.out.println(termList);
            }
            System.out.println("count:" + count + ",耗时:" + (System.currentTimeMillis() - start) + "-->nshort--开词典--识别企--large");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void fun110() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(outfile1))
        ) {
            String tempString;
            PerceptronLexicalAnalyzer segment = (PerceptronLexicalAnalyzer) new PerceptronLexicalAnalyzer(fileRoot + "data/model/perceptron/large/cws.bin",
                    HanLP.Config.PerceptronPOSModelPath,
                    HanLP.Config.PerceptronNERModelPath).enableCustomDictionary(false);
            int line = 0, count = 0;
            segment.learn("安徽 亦政堂 物流 有限公司");
            long start = System.currentTimeMillis();
            while ((tempString = reader.readLine()) != null && line < 1000) {
                line++;
                Sentence termList = segment.analyze(tempString);
                if (termList.size() == 1) {
                    count++;
                    System.out.println(termList);
                }
            }
            System.out.println("count:" + count + ",耗时:" + (System.currentTimeMillis() - start) + "-->perceptron--禁词典--识别企--large");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun111() throws IOException {

        PerceptronSegmenter perceptronSegmenter = new PerceptronSegmenter(fileRoot + "data/model/perceptron/large/cws.bin");
        boolean flag = true;
        int learncount = 0;
        while (flag) {
            learncount++;
            perceptronSegmenter.learn("安徽 亦政堂 物流 有限公司");
            String string = perceptronSegmenter.segment("安徽亦政堂物流有限公司").toString();
            System.out.println(string);
            flag = !string.equals("[安徽, 亦政堂, 物流, 有限公司]");
        }
        System.out.println(learncount);
        System.out.println(perceptronSegmenter.segment("安徽亦政堂物流有限公司"));
        perceptronSegmenter.getModel().save("C:/programs/MyProgrom/src/main/java/hanlp/model/large/cws.bin");
        PerceptronLexicalAnalyzer segment = (PerceptronLexicalAnalyzer) new PerceptronLexicalAnalyzer("C:/programs/MyProgrom/src/main/java/hanlp/model/large/cws.bin",
                HanLP.Config.PerceptronPOSModelPath,
                HanLP.Config.PerceptronNERModelPath).enableCustomDictionary(false);
        System.out.println(segment.analyze("安徽亦政堂物流有限公司"));

    }
}






















