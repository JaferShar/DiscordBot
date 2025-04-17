package org.example.utilitiy;

import org.example.logic.PrayTimeBuilder;
import org.example.view.HadithEmbed;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.example.Main.DEFAULT_VALUE;

public class HadithTimedRunnable implements Runnable {
    private final Map<String, Map.Entry<Double, Double>> serverToLocation;
    private final DiscordApi api;
    private final HadithScraper scraper;

    public HadithTimedRunnable(Map<String, Map.Entry<Double, Double>> serverToLocation, DiscordApi api, HadithScraper scraper) {
        this.serverToLocation = serverToLocation;
        this.api = api;
        this.scraper = scraper;
    }

    @Override
    public void run() {

        Set<Server> servers = api.getServers();
        for (Server server : servers) {

            Map.Entry<Double, Double> location = serverToLocation.getOrDefault(Long.toString(server.getId()), DEFAULT_VALUE);
            PrayTimeBuilder prayTimeBuilder = new PrayTimeBuilder(location.getKey(), location.getValue());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            HashMap<String, LocalTime> prayerTimes = new HashMap<>();
            prayerTimes.put("Fajr", LocalTime.parse(prayTimeBuilder.fajrTime(), formatter));
            prayerTimes.put("Dhuhr", LocalTime.parse(prayTimeBuilder.dhuhrTime(), formatter));
            prayerTimes.put("Asr", LocalTime.parse(prayTimeBuilder.asrTime(), formatter));
            prayerTimes.put("Maghreb", LocalTime.parse(prayTimeBuilder.maghrebTime(), formatter));
            prayerTimes.put("Isha", LocalTime.parse(prayTimeBuilder.ishaTime(), formatter));

            LocalTime now = LocalTime.now();
            //LocalTime now = prayerTimes.get("Fajr");

            for (Map.Entry<String, LocalTime> prayerTime : prayerTimes.entrySet()) {
                if (now.truncatedTo(java.time.temporal.ChronoUnit.MINUTES).equals(prayerTime.getValue())) {
                    api.getTextChannelsByName("bait-al-hikma").stream().findFirst().ifPresent(channel -> {
                        channel.sendMessage(new HadithEmbed(scraper.retrieveHadith()));
                    });
                    api.getTextChannelsByName("bait-al-hikma").stream().findFirst().ifPresent(channel -> {
                        channel.sendMessage("Es ist Zeit für " + prayerTime.getKey() + ".\nGeh beten ulan");
                    });
                }
            }
        }
    }
}