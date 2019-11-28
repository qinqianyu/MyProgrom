package untils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TxtView {
    private static final String fileName = "C:\\Users\\24109\\AppData\\Roaming\\feiq\\Recv Files\\mkt_invopt3.txt";

    public static void main(String[] args) {
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString = null;
            long line = 0;
            while ((tempString = reader.readLine()) != null && line < 100) {
                System.out.println(tempString);
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
