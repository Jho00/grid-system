package broker.serveractions;

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

    public void setTask(Runnable task) {
        tasks.add(task);
    }

    public void setTask(Iterable<Runnable> tasks) {
        tasks.forEach(this::setTask);
    }

    @Override
    public void run() {
        while (true) {
            if (tasks.isEmpty()) {
                return;
            }
            Runnable task = tasks.poll();
            pool.submit(task);
        }
    }

    public static TaskQueueAction makeTaskQueue(Iterable<Runnable> tasks) {
        TaskQueueAction queue = new TaskQueueAction();
        queue.setTask(tasks);

        return queue;
    }

    public static TaskQueueAction makeTaskQueue(Runnable task) {
        TaskQueueAction queue = new TaskQueueAction();
        queue.setTask(task);

        return queue;
    }
}
