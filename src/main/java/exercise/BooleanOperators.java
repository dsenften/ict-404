package exercise;

import java.util.Stack;
import java.util.function.Function;

@SuppressWarnings("StringConcatenationInLoop")
public class BooleanOperators {

    private final static int x = 745;
    private final static int y = 937;

    /**
     * See http://interactivepython.org/courselib/static/pythonds/BasicDS/ConvertingDecimalNumberstoBinaryNumbers.html
     */
    private static String baseConverter(int value, int base) {

        String digits = "0123456789ABCDEF";
        Stack<Integer> stack = new Stack<>();

        while (value > 0) {
            int reminder = value % base;
            stack.push(reminder);
            value /= base;
        }

        String str = "";
        while (!stack.empty()) {
            str += digits.charAt(stack.pop());
        }

        return str;
    }

    public static void main(String[] args) {

        display((x, y) -> x & y);
        display((x, y) -> x | y);
        display((x, y) -> x ^ y);
    }

    private static void display(Calculator calc) {
        int result = calc.calculate(x, y);

        System.out.println("[1] = '" + baseConverter(x, 2) + "'");
        System.out.println("[2] = '" + baseConverter(y, 2) + "'");
        System.out.println("    → '0" + baseConverter(result, 2) + "'");

        System.out.println();
    }

    public interface Calculator {
        int calculate(int x, int y);
    }
}
