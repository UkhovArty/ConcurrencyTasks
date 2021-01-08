package exc93;

import exc86.EasyLock;

public interface ReadWriteLock {
    EasyLock readLock();
    EasyLock writeLock();
}
