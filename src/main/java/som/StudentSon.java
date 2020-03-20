package som;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-17 20:54
 **/
public class StudentSon extends Student {


    public static void main(String[] args) throws InterruptedException {
        Student stu =new StudentSon();

        TestThread a = new TestThread(stu,"1");
        TestThread b = new TestThread(stu,"2");
        TestThread c = new TestThread(stu,"3");
        a.start();
        b.start();
        c.start();
        Thread.sleep(2000);
        int aa = 3;
        int bb = aa+3;
        while(true){
            List<String> aaa = new ArrayList<String>();
            for(;aa<bb;aa++){
                aaa.add(aa+"");
            }
            bb+=3;
            Student.id = aaa;
            Thread.sleep(1100);
        }



    }
}
