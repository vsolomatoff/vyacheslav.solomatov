package atomicReference;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        while(true) {
            int existingValue = get();
            int newValue = existingValue + 1;
            if(count.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    public int get() {
        //System.out.println("Started CASCount.get");
        //System.out.println("    count = " + count);
        //System.out.println("    count.get() = " + count.get());
        return count.get();
    }
}
