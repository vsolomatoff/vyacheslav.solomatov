package nonBlokingCache;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    ConcurrentHashMap<Integer, Base> concurrentHashMap = new ConcurrentHashMap<>();

    public void add(Base model) {
        concurrentHashMap.putIfAbsent(model.getId(), model);
    }

    public Base update(Base model) throws OptimisticException {
        return concurrentHashMap.computeIfPresent(model.getId(), (integer, base) -> {
            if (concurrentHashMap.get(base.getId()).getVersion() == model.getVersion()) {
                model.setVersion(base.getVersion() + 1);
                model.setName(Thread.currentThread().getName());
            }
            return model;
        });
    }

    public void delete(Base model) {
            concurrentHashMap.remove(model.getId());
    }

    public int size() {
        return concurrentHashMap.size();
    }

    @Override
    public String toString() {
        return "Cache{" +
                "concurrentHashMap=" + concurrentHashMap +
                '}';
    }
}
