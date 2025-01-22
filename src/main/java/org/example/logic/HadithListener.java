package org.example.logic;

import org.example.utilitiy.HadithScraper;
import org.example.view.HadithEmbed;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class HadithListener implements MessageCreateListener {

    private final HadithScraper scraper;

    public HadithListener(HadithScraper scraper) {
        this.scraper = scraper;
    }


    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (event.getMessage().getContent().equals("!hadith")) {

            HadithEmbed embed = new HadithEmbed(scraper.retrieveHadith());
            event.getChannel().sendMessage(embed);
        }
    }
}
