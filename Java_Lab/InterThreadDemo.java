class Shared {
    private boolean ready = false;
    synchronized void produce() throws InterruptedException {
        System.out.println("Producing...");
        ready = true;
        notify();
    }
    synchronized void consume() throws InterruptedException {
        while (!ready) wait();
        System.out.println("Consuming...");
    }
}

public class InterThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Shared s = new Shared();
        Thread producer = new Thread(() -> {
            try { Thread.sleep(100); s.produce(); } catch (Exception e) {}
        });
        Thread consumer = new Thread(() -> {
            try { s.consume(); } catch (Exception e) {}
        });
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
    }
}
