package lambda;

public class ThreadExample {

    public static void main(String[] args) {

        // Implement Runnable interface
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("It's running.");
            }
        }).start();

        // Use lambda expression
        new Thread(() -> {
            System.out.println("It's running.");
        }).start();
    }
}
