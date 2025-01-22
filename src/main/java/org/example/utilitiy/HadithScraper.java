package org.example.utilitiy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class HadithScraper {


    private final WebDriver driver;

    public HadithScraper() {
        driver = new FirefoxDriver();
    }


    //TODO is only for Bukhari, change it to every author
    public WebElement retrieveHadith() {
        driver.get("https://sunnah.com/bukhari");

        Random r = new Random();
        int numberOfBooks = 97;
        int random = r.nextInt(numberOfBooks + 1) + 1;

        WebElement element = driver.findElements(By.cssSelector(".book_title.title")).get(random);
        System.out.println("Randomly selected element text: " + element.getText());
        element.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        List<WebElement> hadithContainer = driver.findElements(By.className("actualHadithContainer"));

        Random r2 = new Random();

        random = r2.nextInt(hadithContainer.size());
        System.out.println("Randomly selected element text: " + hadithContainer.get(1).getText());
        System.out.println("This is the number of hadiths: " + hadithContainer.size() + " " + random);
        System.out.println("Randomly selected element text: " + hadithContainer.get(random).getText());

        WebElement hadithEnglish = hadithContainer.get(random).findElement(By.className("english_hadith_full"));

        return hadithContainer.get(random);
    }

}
