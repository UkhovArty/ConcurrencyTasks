public class TASBarrier implements Barrier {
    private final EasyLock lock = new TTASLock();
    private volatile Integer counter = 0;
    public final Integer numOfThreads;

    public TASBarrier(Integer numOfThreads) {
        this.numOfThreads = numOfThreads;
    }

    public void await() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
        while (!counter.equals(numOfThreads)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
