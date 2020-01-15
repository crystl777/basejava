package com.urise.webapp;

public class DeadLock {

    private final static Object FIRST_ENTITY = new Object();
    private final static Object SECOND_ENTITY = new Object();

    public static void main(String[] args) {

     /*
     создаем один поток, внутри которого блокируем сначала первый объект (при вызове метода synchronized,
     и вызываем метод yield, который заставляет sleep первый поток), а после пытаемся заблокировать второй объект
     методом synchronized

     во втором потоке всё тоже самое, только сначала блокируем второй объект и делаем  sleep потока,
     а после пытаемся заблокировать первый объект.
      */

        Thread thread_one = new Thread() {
            public void run() {
                synchronized (FIRST_ENTITY) {
                    Thread.yield();
                    synchronized (SECOND_ENTITY) {
                        System.out.println("Done");
                    }
                }
            }
        };

        Thread thread_two = new Thread() {
            public void run() {
                synchronized (SECOND_ENTITY) {
                    Thread.yield();
                    synchronized (FIRST_ENTITY) {
                        System.out.println("Done");
                    }
                }
            }
        };

        thread_one.start();
        thread_two.start();
    }
}
