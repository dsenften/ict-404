package lambda;

@FunctionalInterface
interface MyInterface {
    int myFunction(String s);
}

@SuppressWarnings("Convert2MethodRef")
public class Processor {
    public static void main(String[] args) {
        MyInterface proc = (String s) -> s.length();

        int length = proc.myFunction("Daniel");
        System.out.println(length); // 6
    }
}
