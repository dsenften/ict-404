package academy.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    void testDefaultDelimiters() {
        String input = "1\n2,3";
        assertEquals(6, calculator.add(input));
    }

    @Test
    void testMultiDelimiters() {
        String input = "//[***][%]\n1***2%3";
        assertEquals(6, calculator.add(input));
    }

    @Test
    void testNumbersMoreThan1000() {
        String input = "//[***][%]\n1***2%3,2000";
        assertEquals(6, calculator.add(input));
    }
}
