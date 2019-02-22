package lambda;


import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SuppressWarnings("ALL")
public class FunctionTest {

    @Test
    public void testUnaryOperator() {
        UnaryOperator<String> s = String::toUpperCase;
        assertThat(s.apply("Daniel"), is("DANIEL"));
    }

    @Test
    public void testBinaryOperator() {
        BinaryOperator<String> s = String::concat;
        assertThat(s.apply("Daniel", "Senften"), is("DanielSenften"));
    }
}
