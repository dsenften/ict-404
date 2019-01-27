package academy.calculator;

public class Calculator {

    public static void main(String[] args) {
        String numbers = "1,2,3,4";
        System.out.println(" numbers = " + numbers);

        StringCalculator calculator = new StringCalculator();
        int output = calculator.add(numbers);
        System.out.println(" output = " + output);
    }
}
