public class ArrayBarrier implements Barrier {
    private final EasyLock lock;
    private final Integer[] array;

    public ArrayBarrier(Integer numOfThreads) {
        array = new Integer[numOfThreads];
        lock = new QueryLock(numOfThreads);
    }

    @Override
    public void await() {
        lock.lock();
        try {
            array[]
        }
    }
}
