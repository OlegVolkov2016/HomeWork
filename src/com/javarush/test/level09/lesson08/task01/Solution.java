package com.javarush.test.level09.lesson08.task01;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * JavaRush.ru
 * Level 09, Lesson 08, Task 01
 * <p/>
 * Есть метод, который выбрасывает два исключения унаследованные от Exception и два унаследованных от RuntimeException:
 * NullPointerException, ArithmeticException, FileNotFoundException, URISyntaxException.
 * Нужно перехватить NullPointerException и FileNotFoundException, но не перехвватывать ArithmeticException и
 * URISyntaxException. Как это сделать?
 * <p/>
 * Date: 09.04.13
 * @author Sergey Kandalintsev
 */

public class Solution
{
    public static void main( String[] args ) throws Exception
    {
        try
        {
            method1();
        }
        catch ( NullPointerException e )
        {
            System.out.println( e );
        }
        catch ( FileNotFoundException e )
        {
            System.out.println( e );
        }
    }

    public static void method1()
            throws NullPointerException, ArithmeticException, FileNotFoundException, URISyntaxException
    {
        int i = ( int ) ( Math.random() * 4 );
        if ( i == 0 )
        {
            throw new NullPointerException();
        }
        if ( i == 1 )
        {
            throw new ArithmeticException();
        }
        if ( i == 2 )
        {
            throw new FileNotFoundException();
        }
        if ( i == 3 )
        {
            throw new URISyntaxException( "", "" );
        }
    }
}