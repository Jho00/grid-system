package broker.entity;


public class Task implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello world");
    }
}
