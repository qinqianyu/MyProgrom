package linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

/**
 * 采集CPU使用率
 */
public class CpuUsage extends ResourceUsage {

    private static Logger log = Logger.getLogger(CpuUsage.class);
    private static CpuUsage INSTANCE = new CpuUsage();

    private CpuUsage() {
    }

    public static CpuUsage getInstance() {
        return INSTANCE;
    }

    /**
     * Purpose:采集CPU使用率
     *
     * @param args
     * @return float, CPU使用率, 小于1
     */
    @Override
    public float get() {
        log.info("开始收集cpu使用率");
        float cpuUsage = 0;
        Runtime r = Runtime.getRuntime();
        try {
            //第一次采集CPU时间
            CpuInfo cpuInfo1 = gather(r);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                log.error("CpuUsage休眠时发生InterruptedException. " + e.getMessage());
                log.error(sw.toString());
            }
            //第二次采集CPU时间
            CpuInfo cpuInfo2 = gather(r);
            if (cpuInfo1.getIdleCpuTime() != 0 && cpuInfo1.getTotalCpuTime() != 0 && cpuInfo2.getIdleCpuTime() != 0 && cpuInfo2.getTotalCpuTime() != 0) {
                cpuUsage = 1 - (float) (cpuInfo2.getIdleCpuTime() - cpuInfo1.getIdleCpuTime()) / (float) (cpuInfo2.getTotalCpuTime() - cpuInfo1.getTotalCpuTime());
                log.info("本节点CPU使用率为: " + cpuUsage);
            }
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error("CpuUsage发生InstantiationException. " + e.getMessage());
            log.error(sw.toString());
        }
        return cpuUsage;
    }

    private CpuInfo gather(Runtime r) throws IOException {
        String command = "cat /proc/stat";
        long endTime = System.currentTimeMillis();
        Process pro = r.exec(command);
        BufferedReader in2 = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        long idleCpuTime2 = 0, totalCpuTime2 = 0;    //分别为系统启动后空闲的CPU时间和总的CPU时间
        String line = null;
        while ((line = in2.readLine()) != null) {
            if (line.startsWith("cpu")) {
                line = line.trim();
                log.info(line);
                String[] temp = line.split("\\s+");
                idleCpuTime2 = Long.parseLong(temp[4]);
                for (String s : temp) {
                    if (!s.equals("cpu")) {
                        totalCpuTime2 += Long.parseLong(s);
                    }
                }
                log.info("IdleCpuTime: " + idleCpuTime2 + ", " + "TotalCpuTime" + totalCpuTime2);
                break;
            }
        }
        in2.close();
        pro.destroy();
        return new CpuInfo(idleCpuTime2, totalCpuTime2);
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            System.out.println(CpuUsage.getInstance().get());
            Thread.sleep(5000);
        }
    }
}
