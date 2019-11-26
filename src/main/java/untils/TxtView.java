package untils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TxtView {
    private static final String fileName = "C:\\Users\\24109\\AppData\\Roaming\\feiq\\Recv Files\\mkt_invopt.txt";

    public static void main(String[] args) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            long line = 0;
            String s = "";
            while ((tempString = reader.readLine()) != null) {

                String tmp = tempString.substring(0, 2);
                if (!tmp.equals(s)) {
                    s = tmp;
                    System.out.println(tempString);
                }
                line++;
                if (line % 1000 == 0) {
                    System.out.println("已经处理" + line + "行");
                }
                if (line % 100000 == 0) {
                    System.out.println("------------已经处理" + line + "行");
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
