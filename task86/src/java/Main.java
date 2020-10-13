public class Main {
    public static void main(String[] args) {
        int n = 10;
        Barrier barrier = new TASBarrier(n);
        for (int i = 0; i < n; i++) {
            new Thread(() -> {
                foo();
                barrier.await();
                bar();
            }).start();
        }
    }

    private static void foo() {
        System.out.println("Hey, I've just met you!");
    }

    private static void bar() {
        System.out.println("And this is crazy!");
    }
}
