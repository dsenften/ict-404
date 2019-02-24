package lambda;

/**
 * Non generic functional interface with an abstract
 * generic function
 */
@FunctionalInterface
public interface Computer {

    <T> void process(T[] list);

}
