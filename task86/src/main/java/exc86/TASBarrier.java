package exc86;

import java.util.concurrent.atomic.AtomicInteger;

public class TASBarrier implements Barrier {
    private final EasyLock lock = new TTASLock();
    private AtomicInteger counter = new AtomicInteger();
    public final int numOfThreads;

    public TASBarrier(int numOfThreads) {
        this.numOfThreads = numOfThreads;
        this.counter.set(0);
    }

    @Override
    public void await() {
        lock.lock();
        try {
            this.counter.incrementAndGet();
        } finally {
            lock.unlock();
        }

        while (counter.get() < numOfThreads) {
        }
    }
}
