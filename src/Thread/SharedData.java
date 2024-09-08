package Thread;

import java.util.concurrent.CountDownLatch;

// CountDownLatch không giải quyết được thứ tự các luồng
public class SharedData {

    int data;
    boolean isProduced = false;

    boolean firstConsumed = false;
    boolean secondConsumed = false;

    int maxThread;
    int counter;

    CountDownLatch latch;

    public SharedData(){
        isProduced = false;
    }

    public SharedData(int maxThread){
        counter = 1;
        isProduced = false;
        this.maxThread = maxThread;
    }

    public synchronized void produce() throws InterruptedException {
        while(isProduced) this.wait();

        this.data = (int)Math.round(Math.random() * 100);
        System.out.println("Produced: " + this.data);

        isProduced = true;
        firstConsumed = false;
        secondConsumed = false;

        this.notifyAll();
    }

    public synchronized void consume(int order) throws InterruptedException {
        // 1 luồng chỉ được chạy khi luồng ngay trước nó đã chạy xong hoặc nó chưa được chạy
        // Luồng cuối không cần quan tâm đến nó vì không còn luồng nào sau đó.
        while(!isProduced ||
                (order == 1 && firstConsumed) ||
                (order == 2 && (!firstConsumed || secondConsumed))||
                (order == 3 && (!secondConsumed))) {
            this.wait();
        }

        System.out.println("Consumed: " + this.data + " - " + order);
        switch(order)
        {
            case 1:
                firstConsumed = true;
                break;
            case 2:
                secondConsumed = true;
                break;
            case 3:
                isProduced = false;
                this.data = -1;
                break;
        }
        this.notifyAll();
    }

    public synchronized void produce1() throws InterruptedException {
        while(isProduced) this.wait();

        this.data = (int)Math.round(Math.random() * 100);
        System.out.println("Prosuced : " + this.data);
        isProduced = true;
        counter = 1;

        this.notifyAll();
    }

    public synchronized void consume1(int order) throws InterruptedException {
        while(!isProduced || counter != order) this.wait();

        System.out.println("Consumed : " + this.data + " - " + order);
        counter++;

        if(counter > maxThread) isProduced = false;

        this.notifyAll();
    }

}
