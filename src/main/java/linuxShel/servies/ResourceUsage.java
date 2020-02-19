package linuxShel.servies;

import linuxShel.dos.ResourceInfo;

import java.util.List;

public class ResourceUsage extends Thread {
    private JSchExecutor executor;

    public ResourceUsage(JSchExecutor executor) {
        this.executor = executor;
    }

    private static Boolean FLAG = true;

    @Override
    public void run() {
        while (FLAG) {
            List<String> strings = null;
            try {
                strings = executor.execCmd("echo 'date '$[$(date +%s%N)/1000000];iostat -d -x | grep sda");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (strings != null) {
                ResourceInfo resource = parseResource(strings);
                if (resource != null) {
                    System.out.println("io使用率为" + resource.getResourceUsage() + "%");
                }
            } else {
                System.out.println("io使用率：获取不到");
            }

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

    private ResourceInfo parseResource(List<String> strings) {
        ResourceInfo resourceInfo = ResourceInfo.builder().build();
        for (String line : strings) {
            if (line.startsWith("date")) {
                line = line.trim();
                resourceInfo.setGatherTime(Long.parseLong(line.split(" ")[1]));
                continue;
            }
            if (line.startsWith("sda")) {
                line = line.trim();
                String[] temp = line.split("\\s+");
                float util = Float.parseFloat(temp[temp.length - 1]);
                resourceInfo.setResourceUsage(util);
                break;
            }
        }
        return resourceInfo;
    }
}