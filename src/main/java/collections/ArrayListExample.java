package collections;

import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();

        numbers.add(815);
        numbers.add(4711);

        for (Integer number: numbers) {
            System.out.println("Value: " + number);
        }
    }
}
