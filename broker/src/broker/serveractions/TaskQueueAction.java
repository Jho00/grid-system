package broker.serveractions;

import broker.entity.interfaces.Executable;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TaskQueueAction implements Runnable {
    private Queue<Runnable> tasks;
    private ExecutorService pool;

    public TaskQueueAction() {
        tasks = new LinkedList<>();
        pool = Executors.newCachedThreadPool();
    }

    public void setTask(Runnable ex) {
        tasks.add(ex);
    }

    @Override
    public void run() {
        while (true) {
            if (!tasks.isEmpty()) {
                Runnable task = tasks.poll();
                pool.submit(task);
            }
        }
    }
}
