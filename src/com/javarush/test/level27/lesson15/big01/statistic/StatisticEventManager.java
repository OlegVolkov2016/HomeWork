package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

/**
 * Created by Олег Волков on 27.02.2016.
 */
public class StatisticEventManager
{
    private StatisticStorage statisticStorage = new StatisticStorage();

    private static StatisticEventManager instance = new StatisticEventManager();

    private StatisticEventManager() {}

    public static StatisticEventManager getInstance() {
        return instance;
    }

    public void register(EventDataRow data) { statisticStorage.put(data); }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> map = new HashMap<>();

        private StatisticStorage() {
            for (EventType type : EventType.values()) {
                map.put(type, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            map.get(data.getType()).add(data);
        }

        private Map<EventType, List<EventDataRow>> getMap()
        {
            return map;
        }
    }

    public Map<Date, Double> getAllVideosAmount(){
        Map< EventType, List < EventDataRow > > map = statisticStorage.getMap();
        Map<Date, Double> resultMap = new TreeMap<>(new Comparator<Date>()
        {
            @Override
            public int compare(Date o1, Date o2)
            {
                return o2.compareTo(o1);
            }
        });
        if (map!= null){
            List <EventDataRow> listEvent = map.get(EventType.SELECTED_VIDEOS);
            for (EventDataRow v : listEvent){
                VideoSelectedEventDataRow vi = (VideoSelectedEventDataRow) v;
                Calendar cal = Calendar.getInstance();
                cal.setTime(vi.getDate());
                GregorianCalendar g = new GregorianCalendar(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                if (resultMap.containsKey(g.getTime())){
                    double tmp = resultMap.get(g.getTime())+vi.getAmount()*1d/100;
                    resultMap.remove(g.getTime());
                    resultMap.put(g.getTime(),tmp);
                }
                else resultMap.put(g.getTime(),(double)vi.getAmount()*1d/100);
            }
        }
        return resultMap;
    }

    public Map< Date, Map < String, Integer > > getTotalCooksTime(){
        Map < EventType, List < EventDataRow > > map = statisticStorage.getMap();
        Map<Date, Map<String, Integer>> resultMap = new TreeMap<>(new Comparator<Date>()
        {
            @Override
            public int compare(Date o1, Date o2)
            {
                return o2.compareTo(o1);
            }
        });
        if (map!= null){
            List <EventDataRow> listEvent = map.get(EventType.COOKED_ORDER);
            for (EventDataRow v : listEvent){
                CookedOrderEventDataRow vi = (CookedOrderEventDataRow) v;
                int time = vi.getTime();
                if (time == 0)continue;
                if (time % 60 == 0) time = time / 60; else time = time / 60 + 1;
                Calendar cal = Calendar.getInstance();
                cal.setTime(vi.getDate());
                GregorianCalendar g = new GregorianCalendar(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                if (resultMap.containsKey(g.getTime())){
                    if(resultMap.get(g.getTime()).containsKey(vi.getCookName())){
                        int tmp = resultMap.get(g.getTime()).get(vi.getCookName())+time;
                        resultMap.get(g.getTime()).remove(vi.getCookName());
                        resultMap.get(g.getTime()).put(vi.getCookName(),tmp);
                    }
                    else resultMap.get(g.getTime()).put(vi.getCookName(),time);
                }
                else{
                    resultMap.put(g.getTime(), new TreeMap<String, Integer>(new Comparator<String>()
                    {
                        @Override
                        public int compare(String o1, String o2)
                        {
                            return o1.compareToIgnoreCase(o2);
                        }
                    }));
                    resultMap.get(g.getTime()).put(vi.getCookName(),time);
                }
            }
        }
        return resultMap;
    }
}