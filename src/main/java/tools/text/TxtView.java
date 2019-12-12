package tools.text;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TxtView {
    private static final String fileName = "C:\\Users\\24109\\Desktop\\机构识别\\kafka疑似\\out5.txt";

    public static void main(String[] args) {
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString = null;
            long line = 0;
            while ((tempString = reader.readLine()) != null && line < 1000) {
                //List<Term> list = HanLP.segment(tempString);
                System.out.println(tempString);
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void view(String word, File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString = null;
            long line = 0;
            while ((reader.readLine()) != null && line < 20085800) {
                line++;
            }
            line = 0;
            while ((tempString = reader.readLine()) != null && line < 30) {
                System.out.println(tempString);
                if (tempString.equals(word)) {
                    break;
                }
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        File file = new File("C:\\Users\\24109\\AppData\\Roaming\\feiq\\Recv Files\\mkt_invopt.txt");
        view("大江控股集团电力科技有限公司", file);
    }


}
