package tools.thread;

import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception {
                // TODO Auto-generated method stub
                Test m = new Test();
                return m.getValue();
            }
        });
        executor.execute(future);
        try {
            long start = System.currentTimeMillis();
            String result = future.get(500, TimeUnit.MILLISECONDS);
            System.out.println(System.currentTimeMillis()-start);
            System.out.println(result);
        } catch (InterruptedException e) {
            // TODO: handle exception
            System.out.println("方法执行中断");
            // future.cancel(true);
        } catch (ExecutionException e) {
            System.out.println("Excution异常");
            // TODO: handle exception
            future.cancel(true);
        } catch (TimeoutException e) {
            // TODO: handle exception
            System.out.println("方法执行时间超时");
            //future.cancel(true);
        }
        System.out.println("正在关闭");
        future.cancel(true);
        executor.shutdown();
        System.out.println("爱上大声地");
    }

    public String getValue() {
        System.out.println("支线任务开始-->"+System.currentTimeMillis());
        try {
            for (int i = 2; i < 10; i+=2) {
                Thread.sleep(100);
                System.out.println("任务完成-->"+i+"0%");
            }
        } catch (Exception ignored) {
        }
        return "支线任务已经完成";
    }
}
