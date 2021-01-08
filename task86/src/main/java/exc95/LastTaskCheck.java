package exc95;

public class LastTaskCheck {
    public static void main(String[] args) {
        Account account = new Account();
        Account account1 = new Account();
        Account account2 = new Account();
        Account account3 = new Account();
        Account account4 = new Account();

        account.deposit((int) (Math.random()*150));
        account1.deposit((int) (Math.random()*150));
        account2.deposit((int) (Math.random()*150));
        account3.deposit((int) (Math.random()*150));
        account4.deposit((int) (Math.random()*150));

        new Thread(() -> {
            account.transfer(100, account1);
        }).start();
        new Thread(() -> {
            account1.transfer(100, account2);
        }).start();
        new Thread(() -> {
            account2.transfer(100, account3);
        }).start();
        new Thread(() -> {
            account3.transfer(100, account4);
        }).start();
        new Thread(() -> {
            account4.transfer(100, account);
        }).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        account.deposit(1000);
        account1.deposit(1000);
        account2.deposit(1000);
        account3.deposit(1000);
        account4.deposit(1000);
    }
}
