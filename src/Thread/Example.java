package Thread;

public class Example{
    public static void main(String[] args) throws InterruptedException {
        Order_Counter();
    }

    private static void Order_Boolean() {
        SharedData data = new SharedData();
        Producer p1 = new Producer(data);
        Consumer con1 = new Consumer(data, 1);
        Consumer con2 = new Consumer(data, 2);
        Consumer con3 = new Consumer(data, 3);
        p1.start();
        con1.start();
        con2.start();
        con3.start();
    }

    private static void Order_Counter(){
        SharedData data = new SharedData(1000);
        Producer p1 = new Producer(data);
        p1.start();

        for(int i = 1 ; i <= 1000 ; i++){
            Consumer con = new Consumer(data, i);
            con.start();
        }
    }

}
