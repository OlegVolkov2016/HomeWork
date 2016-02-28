package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;
import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Олег Волков on 27.02.2016.
 */
public class DirectorTablet {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public void printAdvertisementProfit(){
        Map< Date, Double > map = StatisticEventManager.getInstance().getAllVideosAmount();
        double total = 0;
        for (Map.Entry<Date, Double> e : map.entrySet()){
            total+=e.getValue();
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH,"%s - %.2f",String.format(Locale.ENGLISH, "%1$td-%1$tb-%1$tY", e.getKey()),e.getValue()));
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH,"Total - %.2f",total));
    }
    public void printCookWorkloading(){
        Map< Date, Map < String, Integer > > map = StatisticEventManager.getInstance().getTotalCooksTime();
        for (Map.Entry<Date, Map<String, Integer>> e : map.entrySet()){
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%1$td-%1$tb-%1$tY", e.getKey()));
            for (Map.Entry<String,Integer> ee : e.getValue().entrySet())
            {
                ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %d min", ee.getKey(), ee.getValue()));
            }
            ConsoleHelper.writeMessage("");
        }
    }
    public void printActiveVideoSet()
    {
        for (Advertisement advertisement : StatisticAdvertisementManager.getInstance().getActiveVideoSet())
            ConsoleHelper.writeMessage(advertisement.getName() + " - " + advertisement.getHits());
    }
    public void printArchivedVideoSet()
    {
        for (Advertisement advertisement : StatisticAdvertisementManager.getInstance().getArchVideoSet())
            ConsoleHelper.writeMessage(advertisement.getName());
    }
}
