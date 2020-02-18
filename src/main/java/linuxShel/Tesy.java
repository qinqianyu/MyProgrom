package linuxShel;

import com.jcraft.jsch.JSchException;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;


public class Tesy {
    @Test
    public void test1(){
        //sort01.JSchExecutor root139 = new JSchExecutor("root", "123456", "192.168.20.139");
        JSchExecutor root139 = new JSchExecutor("root", "123456", "192.168.106.101");
        try {
            long stsrt = System.currentTimeMillis();
            System.out.println("开始"+stsrt);
            root139.connect();
            System.out.println("连上了"+System.currentTimeMillis());
            //List<String> strings = root139.execCmd("cat /proc/stat");
            List<String> strings = root139.execCmd("echo $[$(date +%s%N)/1000000];cat /proc/stat");
            System.out.println("结束时间"+System.currentTimeMillis());
           strings.forEach((x)-> System.out.print(x+"\n"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
            }
             stsrt = System.currentTimeMillis();
            System.out.println("开始"+stsrt);
            root139.connect();
            System.out.println("连上了"+System.currentTimeMillis());
            //List<String> strings = root139.execCmd("cat /proc/stat");
             strings = root139.execCmd("echo $[$(date +%s%N)/1000000];cat /proc/stat");
            System.out.println("结束时间"+System.currentTimeMillis());
            strings.forEach((x)-> System.out.print(x+"\n"));



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            root139.disconnect();
        }
    }
}
