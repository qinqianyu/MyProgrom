package com.jxk.java.base.thread.ticket;

public class Ticket extends Thread {

    private Integer count = 0;

    @Override
    public void run() {
       fun();
    }


    private void fun()  {
        while (count<=20){
            System.out.println(getName()+"----"+count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count=count+1;
        }
    }
}
