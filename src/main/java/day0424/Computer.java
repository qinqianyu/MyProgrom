//试编写Java代码实现一个计数器类Computer其中包括：
//
//属性value ：用来保存计数器的当前值；
//
//方法increment()： 计数器加一；
//
//方法decrement() ：计数器减一；
//
//方法reset()：计数器清零。
//
//请编写并调试程序。
package day0424;
public class Computer {
    private int value;

    public Computer(){
        this.value = 0;
    }

    public void increment(){
        this.value++;
    }

    public void decrement(){
        this.value--;
    }

    public void reset(){
        this.value = 0;
    }

    public int getValue(){
        return this.value;
    }

//测试类：
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Computer oCount = new Computer();

        System.out.println("计数器的初始值为：" + oCount.getValue());

        oCount.increment();
        oCount.increment();
        oCount.increment();
        System.out.println("加三后的值为：" + oCount.getValue());

        oCount.decrement();
        System.out.println("减一后的值为：" + oCount.getValue());

        oCount.reset();
        System.out.println("初始化后的值为：" + oCount.getValue());

    }

}
