package org.example.logic;

import org.example.view.AthanEmbed;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.AbstractMap;
import java.util.Map;

import static org.example.Main.DEFAULT_VALUE;

public class L implements MessageCreateListener {
    private final Map<String, Map.Entry<Double, Double>> serverToLocation;

    public L(Map<String, Map.Entry<Double, Double>> serverToLocation) {
        this.serverToLocation = serverToLocation;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessage().getContent().equalsIgnoreCase("!athan")) {
            if (event.getServer().isEmpty()) {
                return;
            }

            Map.Entry<Double, Double> location = serverToLocation.getOrDefault(Long.toString(event.getServer().get().getId()), DEFAULT_VALUE);
            EmbedBuilder embed = new AthanEmbed(location.getKey(), location.getValue());
            event.getChannel().sendMessage(embed);
        }
        if(event.getMessage().getContent().equalsIgnoreCase("Assalamu alaikum")) {
            event.getChannel().sendMessage("Ua alaikum assalamu");
        }
    }
}
