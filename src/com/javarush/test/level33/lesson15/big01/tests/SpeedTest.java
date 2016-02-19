package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Олег Волков on 14.02.2016.
 */
public class SpeedTest
{
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        ids.clear();
        Long currentTime = new Date().getTime();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        Long getIdsTime = new Date().getTime();
        return (getIdsTime - currentTime);
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        strings.clear();
        Long currentTime = new Date().getTime();
        for(Long key : ids) {
            strings.add(shortener.getString(key));
        }
        Long getStringsTime = new Date().getTime();
        return (getStringsTime - currentTime);
    }

    @Test
    public void testHashMapStorage() {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        Shortener shortener1 = new Shortener(hashMapStorageStrategy);
        HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener2 = new Shortener(hashBiMapStorageStrategy);
        Set<String> origStrings = new HashSet<>();
        Set<Long> origIds = new HashSet<>();
        for (int i=0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }
        Long time1 = getTimeForGettingIds(shortener1, origStrings, origIds);
        Long time2 = getTimeForGettingIds(shortener2, origStrings, origIds);
        Assert.assertTrue(time1 > time2);
        Set<String> strings = new HashSet<>();
        Long time3 = getTimeForGettingStrings(shortener1, origIds, strings);
        Long time4 = getTimeForGettingStrings(shortener2, origIds, strings);
//        System.out.println(time3 + ", " + time4);
        Float expected = new Float(time3);
        Float actual = new Float(time4);
        Float delta = new Float(5);
        Assert.assertEquals(expected, actual, delta);
    }
}
