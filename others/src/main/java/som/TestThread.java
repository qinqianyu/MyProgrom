package som;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-17 20:56
 **/
public class TestThread extends Thread {

    private Student studentSon;

    private String threadCode;

    public TestThread(Student studentSon, String threadCode) {
        this.studentSon = studentSon;
        this.threadCode = threadCode;
    }

    @Override
    public void run() {
        super.run();
        while(true){

            for(String a:studentSon.id){
                System.out.print(threadCode+"    "+a);
            }
            System.out.println();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
