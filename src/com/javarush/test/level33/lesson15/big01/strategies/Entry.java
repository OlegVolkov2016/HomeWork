package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.Serializable;

/**
 * Created by Олег Волков on 14.02.2016.
 */
public class Entry implements Serializable
{
    int hash;
    Long key;
    String value;
    Entry next;

    public Entry(int hash, Long key, String value, Entry next)
    {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Long getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public int hashCode()
    {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Entry{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
