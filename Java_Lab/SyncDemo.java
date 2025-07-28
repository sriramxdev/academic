class Counter {
    private int count = 0;
    public synchronized void increment() {
        count++;
    }
    public int getCount() { return count; }
}

class MyThread extends Thread {
    Counter counter;
    MyThread(Counter c) { counter = c; }
    public void run() {
        for (int i = 0; i < 1000; i++) counter.increment();
    }
}

public class SyncDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        MyThread t1 = new MyThread(c);
        MyThread t2 = new MyThread(c);
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("Final count: " + c.getCount());
    }
}
