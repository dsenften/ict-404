package lambda.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@SuppressWarnings("ALL")
public class FunctionExample {

    public static <T, R> List<R> map(List<T> list,
                                     Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = map(
                Arrays.asList("Mein ", "Name ", "ist ", "Daniel"),
                (String s) -> s.length()); // <- Funktion

        System.out.println(list); // [5, 5, 4, 6]
    }
}
