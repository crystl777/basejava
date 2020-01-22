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
         sync(FIRST_ENTITY, SECOND_ENTITY);
     });

     Thread thread_two = new Thread(() -> {
         sync(SECOND_ENTITY, FIRST_ENTITY);
     });

        thread_one.start();
        thread_two.start();
    }


    private static void sync (Object first, Object second) {
        synchronized (first) {
            System.out.println("block " + first.toString());
            try {
                Thread.sleep(100);
                System.out.println("sleep " + (Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (second) {
                System.out.println((Thread.currentThread().getName()) + " is done");
            }
        }
    }


}
