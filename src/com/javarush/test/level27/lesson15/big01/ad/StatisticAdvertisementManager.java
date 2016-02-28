package com.javarush.test.level27.lesson15.big01.ad;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Олег Волков on 27.02.2016.
 */
public class StatisticAdvertisementManager
{
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance()
    {
        return ourInstance;
    }

    private StatisticAdvertisementManager()
    {
    }

    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private Set<Advertisement> videoSet = new TreeSet<Advertisement>(new Comparator<Advertisement>()
    {
        @Override
        public int compare(Advertisement o1, Advertisement o2)
        {
            String n1 = o1.getName();
            String n2 = o2.getName();
            return n1.compareToIgnoreCase(n2);
        }
    });

    public Set<Advertisement> getActiveVideoSet()
    {
        videoSet.clear();
        for (Advertisement advertisement : storage.list())
            if (advertisement.getHits() > 0)
                videoSet.add(advertisement);
        return videoSet;
    }
    public Set<Advertisement> getArchVideoSet()
    {
        videoSet.clear();
        for (Advertisement advertisement : storage.list())
            if (advertisement.getHits() == 0)
                videoSet.add(advertisement);
        return videoSet;
    }
}
