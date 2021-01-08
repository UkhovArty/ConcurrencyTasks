package exc86;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Barrier barrier = new TASBarrier(7);
        Barrier barrier1 = new ArrayBarrier(7);
        for (int i = 0; i < 7; ++i) {
            new Thread(() -> {
                foo();
                barrier.await();
                bar();
            }).start();
        }

        Thread.sleep(1000);
        System.out.println("And all the other boys");

        for (int i = 0; i < 7; ++i) {
            new Thread(() -> {
                foo();
                barrier1.await();
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
