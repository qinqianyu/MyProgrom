package hanlp.demo.arraytrie;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class myTest {
    @Test
    public void testchar() throws IOException {
        BufferedWriter writer1 = new BufferedWriter(new FileWriter("./src/main/java/hanlp/demo/arraytrie/out.txt"));
        for (int i = 1; i < 60000; i++) {
            writer1.write(String.valueOf((char) i));
            writer1.newLine();
        }
        writer1.close();
    }

    @Test
    public void testchar2() {
        System.out.println(String.valueOf((char)13000));
    }
}
