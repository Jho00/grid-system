package broker.services;

import broker.serveractions.TaskQueueAction;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QueueService {
    private static volatile QueueService _instance = null;
    private static final int THREAD_COUNT = 5;

    private ExecutorService pool;

    private QueueService() {
        pool = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    public static synchronized QueueService getInstance() {
        if (_instance == null) {
            _instance = new QueueService();
        }

        return _instance;
    }

    public void addTask(Runnable task) {
        TaskQueueAction action = new TaskQueueAction();
        action.setTask(task);

        pool.submit(action);
    }

    public void addTask(Iterable<Runnable> task) {
        TaskQueueAction action = new TaskQueueAction();
        action.setTask(task);

        pool.submit(action);
    }
}
