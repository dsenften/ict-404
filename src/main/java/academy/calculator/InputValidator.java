package academy.calculator;

class InputValidator {

    static void validate(String[] numbers) throws RuntimeException {
        StringBuilder negativeNumbers = new StringBuilder();
        for (String number : numbers) {
            int numInt = Integer.parseInt(number);
            if (numInt < 0) {
                if (negativeNumbers.length() > 0) {
                    negativeNumbers.append(",");
                }
                negativeNumbers.append(numInt);
            }
        }
        if (negativeNumbers.length() > 0) {
            throw new RuntimeException(String.format(
                    "[%s] Negative Zahlen sind nicht erlaubt.",
                    negativeNumbers.toString()));
        }
    }

}
