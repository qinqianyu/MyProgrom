package linuxShel.test;


import linuxShel.servies.Machine;
import org.junit.Test;

import java.util.Set;

public class MyTest {

    @Test
    public void test01() throws InterruptedException {
        System.out.println("检测对象列表：");
        Set<String> machines = Machine.getMachines();
        machines.forEach((x) -> System.out.println(x + "\n"));

        System.out.println("正在添加对象：192.168.20.60");

        Machine.addMachine("root", "123456", "192.168.20.60");
        System.out.println("添加对象：192.168.20.60 成功");

        System.out.println("检测对象列表：");
        machines = Machine.getMachines();
        machines.forEach((x) -> System.out.println(x + "\n"));

        Machine machine = Machine.getMachine("192.168.20.60");
        Boolean dynamicFlag = machine.getDynamicFlag();

        parseFlag(dynamicFlag);
        if (!dynamicFlag) {
            System.out.println("即将开启动态指标");
            machine.startDynamicMonitor();
        }
        parseFlag(machine.getDynamicFlag());

        Thread.sleep(10000);
        machine.stopDynamicMonitor();
        System.out.println("等待状态");
        Thread.sleep(5000);
        parseFlag(machine.getDynamicFlag());

    }

    static void parseFlag(boolean dynamicFlag) {
        if (dynamicFlag) {
            System.out.println("动态指标状态是：开启");
        } else {
            System.out.println("动态指标状态是：关闭");
        }
    }


}
