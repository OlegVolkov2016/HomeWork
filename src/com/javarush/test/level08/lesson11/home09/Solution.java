package com.javarush.test.level08.lesson11.home09;

import java.util.Date;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Не забудьте учесть первый день года.
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(isDateOdd("JANUARY 1 2016"));
        System.out.println(isDateOdd("JANUARY 4 2014"));
    }

    public static boolean isDateOdd(String date)
    {
        Date startDate = new Date("JANUARY 1 2000");
        Date currentDate = new Date(date);
        startDate.setYear(currentDate.getYear());
        int days = (int) ((currentDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000) + 1);
        if (days % 2 == 0) {
            return false;
        }
        else
        {
            return true;
        }
    }
}
