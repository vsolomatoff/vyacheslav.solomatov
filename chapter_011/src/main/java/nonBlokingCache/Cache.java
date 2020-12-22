package nonBlokingCache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    ConcurrentHashMap<Integer, Base> concurrentHashMap = new ConcurrentHashMap<>();

    public void add(Base model) {
        concurrentHashMap.put(model.id, model);
    }

    public void update(Base model) throws OptimisticException {
        //System.out.println("Started Cache.update - " + Thread.currentThread().getName());
        BiFunction<Integer, Base, Base> biFunction = (integer, base) -> {
            if (concurrentHashMap.get(model.id).version!=model.version) {
                throw new OptimisticException("Выброшено исключение OptimisticException");
            } else {
                base.name = Thread.currentThread().getName();
                base.version++;
            }
            System.out.println("    base = " + base + ", " + Thread.currentThread().getName());
            return (Base) base;
        };
        concurrentHashMap.computeIfPresent(model.id, biFunction);
        //System.out.println("Finished Cache.update - " + Thread.currentThread().getName());
    }

    public void delete(Base model) {
        if (concurrentHashMap.contains(model)) {
            concurrentHashMap.remove(model.id);
        }
    }


    @Override
    public String toString() {
        return "Cache{" +
                "concurrentHashMap=" + concurrentHashMap +
                '}';
    }
}
