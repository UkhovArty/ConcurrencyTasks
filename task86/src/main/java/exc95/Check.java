package exc95;

public class Check {

    public static void main(String[] args) {
        Account account = new Account();
        for (int i = 1; i < 11; ++i) {
            if (i % 3 == 0) {
                new Thread(() -> {
                    account.deposit(500);
                }).start();
            } else if (i % 5 == 0) {
                new Thread(() -> {
                    account.withdrawPreferred(100);
                }).start();
            } else {
                new Thread(() -> {
                    account.withdraw(100);
                }).start();
            }
        }
    }
}