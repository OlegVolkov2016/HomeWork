package com.javarush.test.level14.lesson08.bonus01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            int[] a = new int[1];
            a[1] = 1;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            List<Integer> a = new ArrayList<>();
            int i = a.get(0);

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            int i = -1;
            int[] a = new int[i];

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            List<Integer> a = null;
            a.get(0);

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            int i = Integer.parseInt("A");

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            String s = "A";
            char c = s.charAt(1);

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try {
            FileInputStream fis = new FileInputStream("1");
        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.close();
            reader.readLine();
        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try {
            throw new ClassCastException();
        } catch (Exception e)
        {
            exceptions.add(e);
        }

    }
}
