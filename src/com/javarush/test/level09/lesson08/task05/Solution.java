package com.javarush.test.level09.lesson08.task05;

/**
 * JavaRush.ru
 * Level 09, Lesson 08, Task 05
 * <p/>
 * То же задание что и level09.lesson08.task02, но: <b />
 * Напиши catch, который перехватит Exception2, Exception3, но не Exception1. <b />
 * Подсказка:<b />
 * <p/>
 * <p/>
 * Date: 15.04.13
 * @author Sergey Kandalintsev
 */
public class Solution
{
    static class Exception1 extends Exception
    {
    }

    static class Exception2 extends Exception1
    {
    }

    static class Exception3 extends Exception2
    {
    }

    public static void main( String[] args ) throws Exception
    {
        try
        {
            method1();
        }
        catch ( Exception2 e )
        {
            System.out.println( e );
        }
    }

    public static void method1() throws Exception1, Exception2, Exception3
    {
        int i = ( int ) ( Math.random() * 3 );
        if ( i == 0 )
        {
            throw new Exception1();
        }
        if ( i == 1 )
        {
            throw new Exception2();
        }
        if ( i == 2 )
        {
            throw new Exception3();
        }
    }
}