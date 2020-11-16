package com.jxk.java.base.thread;

import com.jxk.java.base.thread.ticket.Ticket;
import com.jxk.java.base.thread.until.ThreadUtils;
import org.junit.Test;

import java.util.ArrayList;

public class MyConTest {

    /**
     * 通过继承Thread类实现多线程
     */
    @Test
    public void test1() {
        MyThread thread = new MyThread();
        thread.start();
        ThreadUtils.printIdAndName(thread.getId(), thread.getName());

        //*******************************

        MyThread thread1 = new MyThread("线程1");
        thread1.start();
        thread1.setName("线程1-");
        ThreadUtils.printIdAndName(thread1.getId(), thread1.getName());

    }

    /**
     * 通过实现Runnable接口
     */
    @Test
    public void test2() {
        Thread thread = new Thread(new MyRunnable());
        Thread thread0 = new Thread(new MyRunnable(), "线程名");
        thread.start();
        ThreadUtils.printIdAndName(thread.getId(), thread.getName());
        //*******************************
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        });
        //******************************
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                }
            }
        };
        thread1.start();
        ThreadUtils.printIdAndName(thread1.getId(), thread1.getName());
    }



}
