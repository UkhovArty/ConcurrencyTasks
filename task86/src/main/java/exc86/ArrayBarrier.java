package exc86;

import java.util.concurrent.atomic.AtomicInteger;

public class ArrayBarrier implements Barrier {
    private int numOfThreads;
    private volatile int[] array;
    private AtomicInteger num;

    public ArrayBarrier(int numOfThreads) {
        this.numOfThreads = numOfThreads;
        this.array = new int[numOfThreads];
        this.num = new AtomicInteger(0);
    }

    @Override
    public void await() {
        int threadNum = num.getAndIncrement();
        if (threadNum == 0) {
            array[threadNum] = 1;
        } else if (array[threadNum - 1] == 1) {
            array[threadNum] = 1;
        }
        if (threadNum == numOfThreads - 1) {
            array[threadNum] = 2;
            return;
        }
        while (array[threadNum + 1] != 2) {
            Thread.onSpinWait();
        }
        array[threadNum] = 2;
    }
}

