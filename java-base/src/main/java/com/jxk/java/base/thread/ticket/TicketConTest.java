package com.jxk.java.base.thread.ticket;

public class TicketConTest {
    public static void main(String[] args) {
        TicketConTest ticketConTest = new TicketConTest();
        ticketConTest.test1();
    }



    private void test1(){
        Ticket ticket = new Ticket();
        Thread thread1 = new Thread(ticket, "线程1");
        thread1.start();
        System.out.println("****"+ticket.getName());
        System.out.println("****"+thread1.getName());

    }
}
