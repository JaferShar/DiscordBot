package org.example.view;

import org.example.logic.PrayTimeBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;

public class AthanEmbed extends EmbedBuilder {

    public AthanEmbed(double longitude, double latitude) {
        PrayTimeBuilder prayTimeBuilder = new PrayTimeBuilder(latitude, longitude);

        this.setTitle("Athan")
                .setTitle("Date")
                .setDescription(
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                                + " | " + HijrahDate.now(ZoneId.of("Europe/Berlin")).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                                + " | " + HijrahDate.now(ZoneId.of("Europe/Berlin")))
                .setAuthor("Bait Al-Hikma")
                .addField("Fajr", prayTimeBuilder.fajrTime())
                .addField("Dhuhr", prayTimeBuilder.dhuhrTime())
                .addField("Asr", prayTimeBuilder.asrTime())
                .addField("Maghreb", prayTimeBuilder.maghrebTime())
                .addField("Isha", prayTimeBuilder.ishaTime())
                .setColor(Color.white)
                .setImage(new File("src/main/resources/AthanEmbed.png"));
    }


}
