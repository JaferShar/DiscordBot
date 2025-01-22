package org.example.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PrayTimeBuilder {

    private final int fajr = 0;
    private final int sunrise = 1;
    private final int dhuhr = 2;
    private final int asr = 3;
    private final int maghreb = 5;
    private final int isha = 6;
    private final double latitude;
    private final double longitude;
    ArrayList<String> prayerTimes;

    public PrayTimeBuilder(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        double timezone = 1;

        // Test Prayer times here
        PrayTime prayers = new PrayTime();

        prayers.setTimeFormat(prayers.Time24);
        prayers.setCalcMethod(prayers.Makkah);
        prayers.setAsrJuristic(prayers.Shafii);
        prayers.setAdjustHighLats(prayers.MidNight);
        int[] offsets = {0, 0, 0, 0, 0, 0, 0}; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
        prayers.tune(offsets);

        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        prayerTimes = prayers.getPrayerTimes(cal,
                latitude, longitude, timezone);
        ArrayList<String> prayerNames = prayers.getTimeNames();

        for (int i = 0; i < prayerTimes.size(); i++) {
            System.out.println(prayerNames.get(i) + " - " + prayerTimes.get(i));
        }

    }


    public String fajrTime() {

        return prayerTimes.get(fajr);
    }

    public String sunriseTime() {
        return prayerTimes.get(sunrise);
    }

    public String dhuhrTime() {
        return prayerTimes.get(dhuhr);
    }

    public String asrTime() {
        return prayerTimes.get(asr);
    }

    public String maghrebTime() {
        return prayerTimes.get(maghreb);
    }

    public String ishaTime() {
        return prayerTimes.get(isha);
    }
}
