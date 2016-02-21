package com.javarush.test.level38.lesson06.home01;

/**
 * Created by Олег Волков on 20.02.2016.
 */
public class ExceptionFactory
{
    public static <T extends java.lang.Enum> Throwable getException(T e) {
        if (e != null)
        {
            String message = e.toString().substring(0, 1) + e.toString().substring(1).toLowerCase().replaceAll("_", " ");
            if (e instanceof ExceptionApplicationMessage)
            {
                return new Exception(message);
            } else if (e instanceof ExceptionDBMessage)
            {
                return new RuntimeException(message);
            } else if (e instanceof ExceptionUserMessage)
            {
                return new Error(message);
            }
        }
        return new IllegalArgumentException();
    }

}
