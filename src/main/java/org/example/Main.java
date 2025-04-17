package org.example;

import org.example.logic.AthanListener;
import org.example.logic.HadithListener;
import org.example.logic.SalamListener;
import org.example.utilitiy.HadithScraper;
import org.example.utilitiy.HadithTimedRunnable;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Map<String, Map.Entry<Double, Double>> map = new ConcurrentHashMap<>();
    public static final Map.Entry<Double, Double> DEFAULT_VALUE = new AbstractMap.SimpleEntry<>(8.403653, 49.006889);

    public static void main(String[] args) throws IOException {

        DiscordApi api = new DiscordApiBuilder()
                .setToken(Files.readString(Path.of("Tokens")))
                .addIntents(Intent.MESSAGE_CONTENT)
                .login()
                .join();

        //TODO api.getServers read for dynamic serverToLocation add

        //For every thread or call to retrieveScaper a new HadithScraper should open (at max. 5)
        HadithScraper scraper = new HadithScraper();

        api.addListener((MessageCreateListener) event -> System.out.println(event.getMessage().getContent()));

        //TODO implement command pattern for event handling
        api.addMessageCreateListener(new AthanListener(map));
        //TODO implement hourly creation of random hadith
        api.addMessageCreateListener(new HadithListener(scraper));
        api.addMessageCreateListener(new SalamListener());

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(new HadithTimedRunnable(map, api, scraper), 0, 1, TimeUnit.MINUTES);
    }
}