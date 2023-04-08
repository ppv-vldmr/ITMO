package org.example;

import org.example.event.EventsStatistic;
import org.example.event.EventsStatisticImpl;

import java.time.Clock;

public class Main {
    public static void main(String[] args) {
        final EventsStatistic eventsStatistic = new EventsStatisticImpl(Clock.systemUTC());
        eventsStatistic.incEvent("register");
        eventsStatistic.incEvent("login");
        eventsStatistic.incEvent("register");
        eventsStatistic.incEvent("register");
        eventsStatistic.incEvent("logout");
        eventsStatistic.incEvent("login");
        eventsStatistic.incEvent("login");
        eventsStatistic.incEvent("logout");
        eventsStatistic.printStatistic();
    }
}
