package org.example.clock;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.concurrent.atomic.AtomicReference;

public class MutableClock extends Clock {
    private final AtomicReference<Clock> innerClock;

    public MutableClock(Clock clock) {
        this.innerClock = new AtomicReference<>(clock);
    }

    public void offset(Duration offset) {
        innerClock.updateAndGet(clock -> Clock.offset(clock, offset));
    }

    @Override
    public ZoneId getZone() {
        return innerClock.get().getZone();
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return innerClock.get().withZone(zone);
    }

    @Override
    public Instant instant() {
        return innerClock.get().instant();
    }
}
