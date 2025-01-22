package org.example.utilitiy;

import org.example.logic.PrayTimeBuilder;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;

import static org.example.Main.DEFAULT_VALUE;

public class HadithTimedRunnable implements Runnable {
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

            LocalTime fajr = LocalTime.parse(prayTimeBuilder.fajrTime(), formatter);
            LocalTime dhuhr = LocalTime.parse(prayTimeBuilder.dhuhrTime(), formatter);
            LocalTime asr = LocalTime.parse(prayTimeBuilder.asrTime(), formatter);
            LocalTime maghreb = LocalTime.parse(prayTimeBuilder.maghrebTime(), formatter);
            LocalTime isha = LocalTime.parse(prayTimeBuilder.ishaTime(), formatter);

            LocalTime now = LocalTime.now();

        }
    }
}
