package org.example.event;

import lombok.RequiredArgsConstructor;
import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EventsStatisticImpl implements EventsStatistic {
    private final Clock clock;
    private final Map<String, List<Instant>> eventToInstantsMap = new ConcurrentHashMap<>();

    @Override
    public void incEvent(String name) {
        final Instant instant = clock.instant();
        eventToInstantsMap.putIfAbsent(name, new CopyOnWriteArrayList<>());
        eventToInstantsMap.get(name).add(instant);
    }

    private double getEventStatisticFrom(List<Instant> instants, Instant leftBound, Instant rightBound) {
        return StreamEx.of(instants)
                .filter(x -> x.compareTo(leftBound) > 0 && x.compareTo(rightBound) <= 0)
                .collect(Collectors.collectingAndThen(Collectors.counting(), x -> x / 60.0));
    }

    @Override
    public double getEventStatisticByName(String name) {
        final Instant rightBound = clock.instant();
        final Instant leftBound = rightBound.minus(Duration.ofHours(1));
        final List<Instant> instants = eventToInstantsMap.getOrDefault(name, Collections.emptyList());
        return getEventStatisticFrom(instants, leftBound, rightBound);
    }

    @Override
    public Map<String, Double> getAllEventStatistic() {
        final Instant rightBound = clock.instant();
        final Instant leftBound = rightBound.minus(Duration.ofHours(1));
        return EntryStream.of(eventToInstantsMap)
                .mapValues(instants -> getEventStatisticFrom(instants, leftBound, rightBound))
                .toMap();
    }

    @Override
    public void printStatistic() {
        EntryStream.of(getAllEventStatistic()).forKeyValue((name, stats) -> System.out.println(name + ": " + stats));
    }

    @Override
    public void clear() {
        this.eventToInstantsMap.clear();
    }
}
