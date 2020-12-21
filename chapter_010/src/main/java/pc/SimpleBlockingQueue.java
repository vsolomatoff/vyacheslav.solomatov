package pc;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    int total;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int total) {
        this.total = total;
    }

    synchronized T poll() {
        T t = queue.poll();
        System.out.println("Полученено: " + t);
        if (t==null) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Исключение типа InterruptedException перехвачено.");
                Thread.currentThread().interrupt();
            }
        } else {
            notify();
        }
        return t;
    }

    synchronized void offer(T value) {
        if (queue.size() >= total) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Исключение типа InterruptedException перехвачено.");
                Thread.currentThread().interrupt();
            }
        } else {
            queue.add(value);
            System.out.println("queue: " + queue);
            notify();
        }
    }

}
