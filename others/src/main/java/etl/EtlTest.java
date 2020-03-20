package etl;

import java.io.*;

public class EtlTest {
    private static final String ofile = "C:/Users/24109/Desktop/同步/a.txt";
    private static final String nfile = "C:/Users/24109/Desktop/同步/a.txt";
    private static final String cfile = "C:/Users/24109/Desktop/同步/a.txt";

    public static void main(String[] args) throws IOException {
        try (BufferedReader oreader = new BufferedReader(new FileReader(new File(ofile)));
             BufferedReader nreader = new BufferedReader(new FileReader(new File(nfile)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(new File(cfile)))
        ) {
            String nline, oline;
            boolean oisend = false;
            while ((nline = nreader.readLine()) != null) {
                if (!oisend) {
                    oline = oreader.readLine();
                    if (oline == null) {
                        writer.write(nline);
                        writer.newLine();
                        oisend = true;
                    }else {



                    }




                } else {
                    writer.write(nline);
                    writer.newLine();
                }
            }
        }

    }

}
