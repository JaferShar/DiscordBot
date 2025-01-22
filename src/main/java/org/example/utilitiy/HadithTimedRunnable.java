package org.example.utilitiy;

import org.example.logic.PrayTimeBuilder;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;

import static org.example.Main.DEFAULT_VALUE;

public class HadithTimedRunnable implements Runnable {
    private int prayer; //TODO enum
    private final Map<String, Map.Entry<Double, Double>> serverToLocation;
    private final DiscordApi api;

    public HadithTimedRunnable(Map<String, Map.Entry<Double, Double>> serverToLocation, DiscordApi api) {
        this.serverToLocation = serverToLocation;
        this.api = api;
    }

    @Override
    public void run() {

        Set<Server> servers = api.getServers();
        for (Server server : servers) {

            Map.Entry<Double, Double> location = serverToLocation.getOrDefault(Long.toString(server.getId()), DEFAULT_VALUE);
            PrayTimeBuilder prayTimeBuilder = new PrayTimeBuilder(location.getKey(), location.getValue());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            LocalDateTime fajr = LocalDateTime.parse("2007-12-03T10:" + prayTimeBuilder.fajrTime());
            LocalDateTime dhuhr = LocalDateTime.parse("2007-12-03T10:" + prayTimeBuilder.dhuhrTime());
            LocalDateTime asr = LocalDateTime.parse("2007-12-03T10:" + prayTimeBuilder.asrTime());
            LocalDateTime maghreb = LocalDateTime.parse("2007-12-03T10:" + prayTimeBuilder.maghrebTime());
            LocalDateTime isha = LocalDateTime.parse("2007-12-03T10:" + prayTimeBuilder.ishaTime());

            LocalDateTime now = LocalDateTime.now();

//            if (now.isAfter(fajr) && now.isBefore(dhuhr)) {
//
//            }
            System.out.println(now);
        }
    }
}
