package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Олег Волков on 14.02.2016.
 */
public class Solution
{
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> setIds = new HashSet<>();
        for (String string : strings) {
            setIds.add(shortener.getId(string));
        }
        return setIds;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> setStrings = new HashSet<>();
        for(Long key : keys) {
            setStrings.add(shortener.getString(key));
        }
        return setStrings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> testStrings = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++) {
            testStrings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Long currentTime = new Date().getTime();
        Set<Long> setIds = getIds(shortener, testStrings);
        Long getIdsTime = new Date().getTime();
        Long deltaIds = getIdsTime - currentTime;
        Helper.printMessage("Время обработки ключей: " + deltaIds.toString() + " ms.");
        currentTime = new Date().getTime();
        Set<String> setStrings = getStrings(shortener, setIds);
        Long getStringsTime = new Date().getTime();
        Long deltaStrings = getStringsTime - currentTime;
        Helper.printMessage("Время обработки строк: " + deltaStrings.toString() + " ms.");
        if (setStrings.equals(testStrings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
//        for (String string : testStrings) {
//            System.out.print(string + ", ");
//        }
//        System.out.println();
//        for (Long id : setIds) {
//            System.out.print(id + ", ");
//        }
//        System.out.println();
//        for (String string : setStrings) {
//            System.out.print(string + ", ");
//        }
//        System.out.println();
    }

    public static void main(String... args) {
        testStrategy(new HashMapStorageStrategy(), 10000L);
        testStrategy(new OurHashMapStorageStrategy(), 10000L);
        testStrategy(new FileStorageStrategy(), 100L);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000L);
        testStrategy(new HashBiMapStorageStrategy(), 10000L);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000L);
    }
}
