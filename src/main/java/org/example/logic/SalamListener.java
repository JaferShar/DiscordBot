package org.example.logic;

import org.example.view.AthanEmbed;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.Map;

import static org.example.Main.DEFAULT_VALUE;

public class SalamListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessage().getContent().equalsIgnoreCase("Assalamu alaikum")) {
            event.getChannel().sendMessage("Ua alaikum assalamu");
        }
    }
}
