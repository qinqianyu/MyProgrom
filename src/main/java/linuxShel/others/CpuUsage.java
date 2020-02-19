package linuxShel.others;

import linuxShel.dos.CpuInfo;
import linuxShel.servies.JSchExecutor;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

/**
 * 采集CPU使用率
 */
public class CpuUsage extends Thread{

    private static Logger log = Logger.getLogger(CpuUsage.class);
    private static CpuUsage INSTANCE = new CpuUsage();
    private static boolean flag = true;
    private static int tmFflag = 60;



    private String host;
    private String user;
    private String passwd;

    public CpuUsage() {
    }

    public static CpuUsage getInstance() {
        return INSTANCE;
    }


    public void startgather(String user, String passwd, String host) {
        JSchExecutor executor = new JSchExecutor(user, passwd, host);
        try {
            executor.connect();
            CpuInfo beforCpuInfo = null;
            while (tmFflag > 0) {
                List<String> strings = executor.execCmd("echo 'date '$[$(date +%s%N)/1000000];cat /proc/stat");
                CpuInfo cpuInfo = parseCpuTime(strings);
                if (beforCpuInfo != null) {
                    float cpuUsage = 100 * (1 - (float) (cpuInfo.getIdleCpuTime() - beforCpuInfo.getIdleCpuTime()) / (float) (cpuInfo.getTotalCpuTime() - beforCpuInfo.getTotalCpuTime()));
                    System.out.println("cpu使用率为" + cpuUsage + "%");
                }
                beforCpuInfo = cpuInfo;
                Thread.sleep(1000);
                tmFflag -= 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.disconnect();
        }
    }

    public void stopgather() {
        flag = false;
    }

    private CpuInfo parseCpuTime(List<String> strings) {
        CpuInfo cpuInfo = CpuInfo.builder().build();
        for (String line : strings) {
            if (line.startsWith("date")) {
                line = line.trim();
                cpuInfo.setGatherTime(Long.parseLong(line.split(" ")[1]));
                continue;
            }
            if (line.startsWith("cpu")) {
                line = line.trim();
                String[] temp = line.split("\\s+");
                cpuInfo.setIdleCpuTime(Long.parseLong(temp[4]));
                long totalCpuTime = 0;
                for (String s : temp) {
                    if (!s.equals("cpu")) {
                        totalCpuTime += Long.parseLong(s);
                    }
                }
                cpuInfo.setTotalCpuTime(totalCpuTime);
                break;
            }
        }
        return cpuInfo;
    }


    /**
     * Purpose:采集CPU使用率
     *
     * @return float, CPU使用率, 小于1
     */

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
        return CpuInfo.builder().idleCpuTime(idleCpuTime2).build();
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) {
        CpuUsage.getInstance().startgather("root", "123456", "192.168.20.139");
    }

    @Override
    public void run() {

    }
}
