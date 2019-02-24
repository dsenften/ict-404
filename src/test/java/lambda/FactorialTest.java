package lambda;


import org.junit.jupiter.api.Test;

import java.util.function.IntUnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://de.wikipedia.org/wiki/Fakultät_(Mathematik)
 */
@SuppressWarnings("ALL")
public class FactorialTest {

    private IntUnaryOperator factorial;

    {
        factorial = n -> n == 0 ? 1 : n * factorial.applyAsInt(n - 1);
    }

    @Test
    public void test5() {
        assertEquals(120, factorial.applyAsInt(5));
    }

    @Test
    public void test10() {
        assertEquals(3_628_800, factorial.applyAsInt(10));
    }

}
