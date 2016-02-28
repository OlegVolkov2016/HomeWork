package com.javarush.test.level27.lesson15.big01.statistic.event;

import java.util.Date;

/**
 * Created by Олег Волков on 27.02.2016.
 */
public interface EventDataRow
{
    EventType getType();
    Date getDate();
    int getTime();
}
