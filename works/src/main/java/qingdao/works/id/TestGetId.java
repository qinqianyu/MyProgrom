package qingdao.works.id;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-27 11:08
 **/
public class TestGetId {
    @Test
    public void test() throws InterruptedException {
        TestId testId = new TestId();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int finalI = i+1;
            Thread thread = new Thread(() -> grtid( finalI, testId ), "th" + i);
            thread.start();
            threads.add(thread);
        }
        for (int i = 0; i < 6; i++) {
            threads.get(i).join();
        }
    }

    private void grtid(int finalI, TestId testId){
        for (int i = 0; i < 10; i++) {
            System.out.println(finalI+"--"+ testId.generateId());
        }
    }
}
