package generics;

public class Cache<T> {
    private T cache;

    public void add(T cache) {
        this.cache = cache;
    }

    public T get() {
        return this.cache;
    }
}
