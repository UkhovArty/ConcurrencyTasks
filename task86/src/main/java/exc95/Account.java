package exc95;

public class Account {
    private int balance;
    private int amountOfPrefTransactions;

    public Account() {
        balance = 0;
        amountOfPrefTransactions = 0;
    }

    public void deposit(int i) {
        synchronized (this) {
            balance += i;
            System.out.println("Add " + i + " balance is " + balance + "\n");
            this.notifyAll();
        }
    }

    public void withdraw(int i) {
        synchronized (this) {
            while (balance < i || amountOfPrefTransactions > 0) {
                sleep();
            }
            balance -= i;
            System.out.println("subtracted " + i + " balance is " + balance + "\n");
        }
    }

    public void withdrawPreferred(int i) {
        synchronized (this) {
            amountOfPrefTransactions++;
            while (balance < i) {
                sleep();
            }
            balance -= i;
            amountOfPrefTransactions--;
            System.out.println("Golden mega transaction " + i + " balance is " + balance + "\n");
        }
    }

    public void transfer(int k, Account reserve) {
        synchronized (this) {
            reserve.withdraw(k);
            deposit(k);
        }
    }

    private void sleep() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}