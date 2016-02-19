package com.javarush.test.level09.lesson08.task02;

/**
 * JavaRush.ru
 * Level 09, Lesson 08, Task 02
 * <p/>
 * Есть три исключения, последовательно унаследованные от Exception: <b />
 * class Exception1 extends Exception <b />
 * class Exception2 extends Exception1 <b />
 * Exception3 extends Exception2 <b />
 * Есть метод, который описан так: <b />
 * public static void method1() throws Exception1, Exception2, Exception3 <b />
 * Напишите catch, который перехватит все три Exception1, Exception2, Exception3.
 * <p/>
 * Date: 09.04.13
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
        catch ( Exception1 e )
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