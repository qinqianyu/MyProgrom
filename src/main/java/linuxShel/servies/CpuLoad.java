package linuxShel.servies;

import linuxShel.dos.CpuInfo;
import linuxShel.dos.CpuThreadInfo;
import redis.clients.jedis.Jedis;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class CpuLoad extends Thread {
    private JSchExecutor executor;

    public CpuLoad(JSchExecutor executor) {
        this.executor = executor;
    }

    private static Boolean FLAG = true;

    @Override
    public void run() {
        CpuInfo beforCpuInfo = null;
        CpuInfo cpuInfo = null;
        Jedis jedis = RedisPoolUtil4J.getConnection();
        String host = executor.getHost();
        String cpukey="cpu-"+host;
        while (FLAG) {
            List<String> strings = null;
            try {
                strings = executor.execCmd("echo 'date '$[$(date +%s%N)/1000000];cat /proc/stat | grep cpu");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (strings != null) {
                cpuInfo = parseCpuTime(strings);
            } else {
                System.out.println("cpu使用率：获取不到");
                return;
            }
            if (beforCpuInfo != null) {
                double cpuUsage = 100 * (1 - (float) (cpuInfo.getIdleCpuTime() - beforCpuInfo.getIdleCpuTime()) / (float) (cpuInfo.getTotalCpuTime() - beforCpuInfo.getTotalCpuTime()));
                Long rpush = jedis.rpush(cpukey,  String.format("%.2f", cpuUsage));
                if (rpush > 60) {
                    jedis.ltrim(cpukey, -60, -1);
                }
                ArrayList<CpuThreadInfo> cpuThreadInfos = cpuInfo.getCpuThreadInfos();
                ArrayList<CpuThreadInfo> beforCpuThreadInfos = beforCpuInfo.getCpuThreadInfos();
                int index=0;
                for (CpuThreadInfo tmp:cpuThreadInfos) {
                    String threadName=cpukey+"-"+index;
                    double threadUsage=100 * (1 - (float) (tmp.getIdleCpuTime() - beforCpuThreadInfos.get(index).getIdleCpuTime()) / (float) (tmp.getTotalCpuTime() - beforCpuThreadInfos.get(index).getTotalCpuTime()));
                    rpush = jedis.rpush(threadName,  String.format("%.2f", threadUsage));
                    if (rpush > 60) {
                        jedis.ltrim(threadName, -60, -1);
                    }
                }


                System.out.println("cpu使用率为" + String.format("%.2f", cpuUsage) + "%");
            }
            beforCpuInfo = cpuInfo;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void setFlag(Boolean flag) {
        FLAG = flag;
    }

    private CpuInfo parseCpuTime(List<String> strings) {
        CpuInfo cpuInfo = CpuInfo.builder().build();
        ArrayList<CpuThreadInfo> cpuThreadInfos = new ArrayList<>();
        for (String line : strings) {
            if (line.startsWith("date")) {
                line = line.trim();
                cpuInfo.setGatherTime(Long.parseLong(line.split(" ")[1]));
                continue;
            }
            if (line.startsWith("cpu ")) {
                line = line.trim();
                String[] temp = line.split("\\s+");
                cpuInfo.setIdleCpuTime(Long.parseLong(temp[4]));
                long totalCpuTime = 0;
                for (String s : temp) {
                    if (!s.startsWith("cpu")) {
                        totalCpuTime += Long.parseLong(s);
                    }
                }
                cpuInfo.setTotalCpuTime(totalCpuTime);
                continue;
            }
            if (line.startsWith("cpu")) {
                CpuThreadInfo threadInfo = CpuThreadInfo.builder().build();
                line = line.trim();
                String[] temp = line.split("\\s+");
                threadInfo.setIdleCpuTime(Long.parseLong(temp[4]));
                long totalCpuTime = 0;
                for (String s : temp) {
                    if (!s.startsWith("cpu")) {
                        totalCpuTime += Long.parseLong(s);
                    }
                }
                threadInfo.setTotalCpuTime(totalCpuTime);
                cpuThreadInfos.add(threadInfo);
                break;
            }
        }
        cpuInfo.setCpuThreadInfos(cpuThreadInfos);
        cpuInfo.setThreadCount(cpuThreadInfos.size());
        return cpuInfo;
    }

}
