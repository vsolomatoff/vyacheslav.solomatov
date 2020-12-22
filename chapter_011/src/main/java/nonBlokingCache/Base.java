package nonBlokingCache;

public class Base {
    int id;
    int version;
    String name;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                '}';
    }
}
