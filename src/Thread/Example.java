package Thread;

import java.util.concurrent.CountDownLatch;

public class Example{
    public static void main(String[] args) throws InterruptedException {
        SharedData data = new SharedData();
        Producer p1 = new Producer(data);
        Consumer con1 = new Consumer(data, "first");
        Consumer con2 = new Consumer(data, "second");
        p1.start();
        con1.start();
        con2.start();
    }
}

class SharedData {

    int data;
    boolean isProducing = false;
    CountDownLatch latch;

    public SharedData(){
        latch = new CountDownLatch(2);
    }

    public synchronized void produce() throws InterruptedException {
        if(isProducing) this.wait();

        this.data = (int)Math.round(Math.random() * 100);
        System.out.println("Produced: " + this.data);
        isProducing = true;
        this.notifyAll();
    }

    public synchronized void consume(String order) throws InterruptedException {
        while(!isProducing) this.wait();

        System.out.println("Consumed: " + this.data + " - " + order);
        latch.countDown();

        if(latch.getCount() == 0){
            this.data = 0;
            isProducing = false;
            latch = new CountDownLatch(2);
            this.notifyAll();
        }
    }

}
