package exc86;

import java.util.concurrent.atomic.AtomicBoolean;

public class TTASLock implements EasyLock {
    private AtomicBoolean state;

    public TTASLock() {
        this.state = new AtomicBoolean(false);
    }

    @Override
    public void lock() {
        while (true) {
            while (state.get()) {
            }

            if (!state.getAndSet(true))
                return;
        }
    }

    @Override
    public void unlock() {
        state.set(false);
    }

}
