package exc93;

public class LockCheck {
    public static void main(String[] args) {
        SimpleReadWriteLock simpleReadWriteLock = new SimpleReadWriteLock();
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 0) {
                new Thread(() -> {
                    write(simpleReadWriteLock);
                }).start();
            } else {
                new Thread(() -> {
                    read(simpleReadWriteLock);
                }).start();
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void write(SimpleReadWriteLock simpleReadWriteLock) {
        simpleReadWriteLock.writeLock().lock();
        System.out.println("Writing something");
        System.out.println("I've finished, ok go");
        simpleReadWriteLock.writeLock().unlock();
    }

    private static void read(SimpleReadWriteLock simpleReadWriteLock) {
        simpleReadWriteLock.readLock().lock();
        System.out.println("Yeet, this I read");
        simpleReadWriteLock.readLock().unlock();
    }
}
