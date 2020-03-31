package qingdao.works.marketplaer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-20 14:49
 **/
@Slf4j
public class TextView {


    private static void view(File file, Integer start, Integer end) {
        if ((end - start) < 0) {
            log.error("终止行数小于起始行数");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            long line = 0;
            while ((reader.readLine()) != null && line < start) {
                line++;
            }
            line = 0;
            while ((tempString = reader.readLine()) != null && line < (end - start)) {
                System.out.println(tempString);
                line++;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void test() {
        File file = new File("C:/Users/24109/Desktop/情报/drop_1.txt");
        view(file, 1, 20);
    }
    @Test
    public void test2() {
        File file = new File("C:/Users/24109/Desktop/情报/out_1.txt");
        view(file, 1, 10);
    }
    @Test
    public void test3() {
        File file = new File("c:\\users\\24109\\appdata\\local\\programs\\python\\python37\\lib\\site-packages\\pyhanlp\\static\\data\\dictionary\\CustomDictionary.txt");
        File filf = new File("C:\\Users\\24109\\AppData\\Local\\Programs\\Python\\Python37\\Lib\\site-packages\\pyhanlp\\static\\data\\dictionary\\custom\\CustomDictionary.txt");
        view(file, 1, 10);
    }
    @Test
    public void test4() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }
}
