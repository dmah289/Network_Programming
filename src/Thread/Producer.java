package Thread;

public class Producer extends Thread {

    private SharedData sharedData;

    public Producer (SharedData data){
        this.sharedData = data;
    }

    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++){
            try {
                sharedData.produce();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
