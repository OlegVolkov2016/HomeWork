package com.javarush.test.level14.lesson08.bonus03;

/**
 * Created by Олег Волков on 24.01.2016.
 */
public class Singleton
{
    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
