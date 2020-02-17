package tools.thread;

public class GetCpuM {
    public static void main(String[] args) {
        int availProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("avail processors count: " + availProcessors);
    }
}
