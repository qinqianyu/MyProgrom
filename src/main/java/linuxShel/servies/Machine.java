package linuxShel.servies;

import com.jcraft.jsch.JSchException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class Machine {

    private Boolean dynamicFlag = false;

    private String host;
    private String user;
    private String passwd;


    private JSchExecutor executor;

    private CpuLoad cpuLoad;
    private MemUsage memUsage;
    private ResourceUsage resourceUsage;

    private static Map<String, Machine> machineList = new HashMap<>();

    public static Machine getMachine(String ip) {
        return machineList.getOrDefault(ip, null);
    }

    public static Set<String> getMachines() {
        return machineList.keySet();
    }

    public static Boolean addMachine(String user, String passwd, String host) {
        if (machineList.containsKey(host)) {
            return false;
        } else {
            Machine machine = new Machine(user, passwd, host);
            machineList.put(host, machine);
            return true;
        }
    }


    public void startDynamicMonitor() {
        try {
            executor.connect();
            cpuLoad = new CpuLoad(executor);
            memUsage = new MemUsage(executor);
            resourceUsage = new ResourceUsage(executor);
            cpuLoad.start();
            memUsage.start();
            resourceUsage.start();
            this.dynamicFlag = true;
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public void stopDynamicMonitor() {
        cpuLoad.setFlag(false);
        memUsage.setFlag(false);
        resourceUsage.setFlag(false);
        this.dynamicFlag = false;
    }

    private Machine(String user, String passwd, String host) {
        this.executor = new JSchExecutor(user, passwd, host);
        this.host = host;
        this.user = user;
        this.passwd = passwd;
    }

    private Machine() {
    }

    private void setDynamicFlag(Boolean flag) {
    }


}
