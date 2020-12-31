package atomicReference;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        do {
            int existingValue = get();
            int newValue = existingValue + 1;
            if (count.compareAndSet(existingValue, newValue)) {
                return;
            }
        } while (true);
    }

    public int get() {
        return count.get();
    }
}
