package lambda.function;

@SuppressWarnings("ALL")
// Generic functional parameter function with
// one type parameter T
public interface Comparator<T> {
    int compare(T o1, T o2);
}
