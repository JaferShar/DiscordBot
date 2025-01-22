package org.example.view;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HadithEmbed extends EmbedBuilder {

    WebElement hadithContainer;

    public HadithEmbed(WebElement hadithContainer) {

        this.hadithContainer = hadithContainer;

        String narrator = hadithContainer.findElement(By.className("hadith_narrated")).getText();
        String hadith = hadithContainer.findElement(By.className("text_details")).getText();
        String footnote = hadithContainer.findElement(By.className("hadith_reference")).getText();

        this.setTitle(narrator)
                .addField("Hadith", hadith)
                .addField("Reference", footnote);
    }


}
