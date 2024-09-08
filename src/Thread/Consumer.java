package Thread;

public class Consumer extends Thread {

    private SharedData sharedData;
    private String order;

    public Consumer(SharedData data, String order){
        sharedData = data;
        this.order = order;
    }

    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++){
            try {
                sharedData.consume(order);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
