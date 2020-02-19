package linuxShel.servies;

import linuxShel.dos.CpuInfo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
                DecimalFormat df = new DecimalFormat("#.00");
                BigDecimal bg = new BigDecimal(cpuUsage);
                cpuUsage = bg.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
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

}
