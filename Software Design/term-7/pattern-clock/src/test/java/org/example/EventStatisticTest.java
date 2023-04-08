package org.example;

import org.example.clock.MutableClock;
import org.example.event.EventsStatistic;
import org.example.event.EventsStatisticImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Map;

public class EventStatisticTest {
    @Test
    public void testEmpty() {
        final Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        final EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);
        Assertions.assertEquals(0.0, eventsStatistic.getEventStatisticByName("test"));
    }

    @Test
    public void testInc() {
        final Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        final EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);
        eventsStatistic.incEvent("test");
        Assertions.assertEquals(1.0 / 60, eventsStatistic.getEventStatisticByName("test"));
    }

    @Test
    public void testMultiple() {
        final Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        final EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);
        eventsStatistic.incEvent("register");
        eventsStatistic.incEvent("login");
        eventsStatistic.incEvent("register");
        eventsStatistic.incEvent("register");
        eventsStatistic.incEvent("register");
        eventsStatistic.incEvent("register");
        eventsStatistic.incEvent("logout");
        eventsStatistic.incEvent("login");
        eventsStatistic.incEvent("login");
        eventsStatistic.incEvent("logout");
        Assertions.assertEquals(5.0 / 60, eventsStatistic.getEventStatisticByName("register"));
        Assertions.assertEquals(3.0 / 60, eventsStatistic.getEventStatisticByName("login"));
        Assertions.assertEquals(2.0 / 60, eventsStatistic.getEventStatisticByName("logout"));
    }

    @Test
    public void testOffset() {
        final MutableClock clock = new MutableClock(Clock.fixed(Instant.now(), ZoneId.systemDefault()));
        final EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);
        eventsStatistic.incEvent("test");
        clock.offset(Duration.ofHours(1));
        Assertions.assertEquals(0.0, eventsStatistic.getEventStatisticByName("test"));
    }

    @Test
    public void testOffsetMultiple() {
        final MutableClock clock = new MutableClock(Clock.fixed(Instant.now(), ZoneId.systemDefault()));
        final EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);
        eventsStatistic.incEvent("register");
        clock.offset(Duration.ofMinutes(5));
        eventsStatistic.incEvent("login");
        clock.offset(Duration.ofMinutes(1));
        eventsStatistic.incEvent("register");
        clock.offset(Duration.ofMinutes(1));
        eventsStatistic.incEvent("register");
        clock.offset(Duration.ofMinutes(2));
        eventsStatistic.incEvent("register");
        clock.offset(Duration.ofMinutes(37));
        eventsStatistic.incEvent("register");
        clock.offset(Duration.ofMinutes(4));
        eventsStatistic.incEvent("logout");
        clock.offset(Duration.ofMinutes(2));
        eventsStatistic.incEvent("login");
        clock.offset(Duration.ofMinutes(8));
        eventsStatistic.incEvent("login");
        clock.offset(Duration.ofMinutes(1));
        eventsStatistic.incEvent("logout");
        clock.offset(Duration.ofMinutes(5));
        final Map<String, Double> expectedMap = Map.of("register", 3.0 / 60, "login", 2.0 / 60, "logout", 2.0 / 60);
        Assertions.assertEquals(expectedMap, eventsStatistic.getAllEventStatistic());
    }

    @Test
    public void testClear() {
        final MutableClock clock = new MutableClock(Clock.fixed(Instant.now(), ZoneId.systemDefault()));
        final EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);
        eventsStatistic.incEvent("test");
        Assertions.assertEquals(1.0 / 60, eventsStatistic.getEventStatisticByName("test"));
        eventsStatistic.clear();
        Assertions.assertEquals(Collections.emptyMap(), eventsStatistic.getAllEventStatistic());
    }
}
