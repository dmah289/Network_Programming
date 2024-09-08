package Thread;

public class Consumer extends Thread {

    private SharedData sharedData;
    private int order;

    public Consumer(SharedData data, int order){
        sharedData = data;
        this.order = order;
    }

    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++){
            try {
                sharedData.consume1(order);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
