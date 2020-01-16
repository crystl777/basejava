package com.urise.webapp;

public class DeadLock {

    private final static Object FIRST_ENTITY = new Object();
    private final static Object SECOND_ENTITY = new Object();

    public static void main(String[] args) {

     /*
     первый поток захватывает первый объект, ждёт какое-то время и пытается захватить второй
     второй поток захватывает второй объект, ждёт какое-то время и пытается захватить первый объект

     без Thread.sleep мы не можем точно утверждать, что возникнет deadlock, так как остаётся шанс, что
     пока Thread#1 блокирует первый объект и, после, второй объект, Thread#2 успеет заблокировать второй объект.

     !!!  Thread.yield использовать в реализации deadlock нельзя. Так как по факту yield - это подсказка планировщику о
     том, что данный поток готов предоставить свое текущее использование процессору, НО планировщик может игнорировать
     данную подсказку.  !!!
      */

        Thread thread_one = new Thread(() -> {
            synchronized (FIRST_ENTITY) {
                System.out.println("block entity 1");
                try {
                    System.out.println("sleep thread 1");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (SECOND_ENTITY) {
                    System.out.println("Done thread 1");
                }
            }
        });

        Thread thread_two = new Thread(() -> {
            synchronized (SECOND_ENTITY) {
                System.out.println("block entity 2");
                try {
                    System.out.println("sleep thread 2");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (FIRST_ENTITY) {
                    System.out.println("Done thread 2");
                }
            }
        });

        thread_one.start();
        thread_two.start();
    }
}
