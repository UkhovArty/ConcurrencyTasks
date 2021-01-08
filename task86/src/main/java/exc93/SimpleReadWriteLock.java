package exc93;


import exc86.EasyLock;

public class SimpleReadWriteLock implements ReadWriteLock {
    private int readers;
    private boolean writer;
    private EasyLock readLock, writeLock;

    public SimpleReadWriteLock() {
        writer = false;
        readers = 0;
        readLock = new ReadLock();
        writeLock = new WriteLock();
    }

    @Override
    public EasyLock readLock() {
        return readLock;
    }

    @Override
    public EasyLock writeLock() {
        return writeLock;
    }


    class ReadLock implements EasyLock {
        @Override
        public void lock() {
            synchronized (SimpleReadWriteLock.this) {
                try {
                    while (writer) {
                        SimpleReadWriteLock.this.wait();
                    }
                    readers++;
                } catch (InterruptedException ignored) {
                }
            }
        }

        @Override
        public void unlock() {
            synchronized (SimpleReadWriteLock.this) {
                readers--;
                if (readers == 0) {
                    SimpleReadWriteLock.this.notifyAll();
                }
            }
        }
    }

    class WriteLock implements EasyLock {
        @Override
        public void lock() {
            synchronized (SimpleReadWriteLock.this) {
                try {
                    while (readers > 0 || writer) {
                        SimpleReadWriteLock.this.wait();
                    }
                    writer = true;
                } catch (InterruptedException ignored) {
                }
            }
        }

        @Override
        public void unlock() {
            synchronized (SimpleReadWriteLock.this) {
                writer = false;
                SimpleReadWriteLock.this.notifyAll();
            }
        }
    }
}