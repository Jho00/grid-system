package broker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static ExecutorService pool;
    public static void main(String[] args) {
        init();


    }

    private static void init() {
        pool = Executors.newCachedThreadPool();
    }
}
