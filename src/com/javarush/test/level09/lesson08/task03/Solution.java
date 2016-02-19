package com.javarush.test.level09.lesson08.task03;

/**
 * JavaRush.ru
 * Level 09, Lesson 08, Task 03
 * <p/>
 * То же задание что и level09.lesson08.task02, но: <b />
 * Напиши catch, который перехватит Exception1, Exception3, но не Exception2. <b />
 * Подсказка:<b />
 * <p/>
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
        catch ( Exception3 e )
        {
            System.out.println( e );
        }
        catch ( Exception2 e )
        {
            throw e;
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