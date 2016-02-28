package com.javarush.test.level40.lesson10.task02;

/* Работа с Joda Time
Выполни задание, используя библиотеку Joda Time версии 2.9.1
Реализуй метод printDate(String date).
Он должен в качестве параметра принимать дату (в одном из 3х форматов)
и выводить ее в консоль в соответсвии с примером:

1) Для "21.4.2014 15:56:45" вывод должен быть:
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1
AM или PM: 1
Часы: 3
Часы дня: 15
Минуты: 56
Секунды: 45

2) Для "21.4.2014":
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1

3) Для "17:33:40":
AM или PM: 1
Часы: 5
Часы дня: 17
Минуты: 33
Секунды: 40
*/

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        DateFormat dateFormat1 = new SimpleDateFormat("dd.M.yyyy HH:mm:ss");
        DateFormat dateFormat2 = new SimpleDateFormat("dd.M.yyyy");
        DateFormat dateFormat3 = new SimpleDateFormat("HH:mm:ss");
        DateTime calendar;
        Date result;
        try
        {
            result = dateFormat1.parse(date);
            calendar = new DateTime(result);

            DateTime minYearDate = calendar.dayOfYear().withMinimumValue();
            DateTime minMonthDate = calendar.dayOfMonth().withMinimumValue();
            int weekDis = (minYearDate.getWeekyear() == calendar.getYear()) ? 0 : 1;
            int weekOfYear = calendar.getWeekOfWeekyear() + weekDis;
            if (weekOfYear >= 53)
                weekOfYear = 1;
            int weekOfMonth = calendar.getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 1;
            if (minMonthDate.getWeekOfWeekyear() >= calendar.getWeekOfWeekyear())
                weekOfMonth = calendar.minusDays(7).getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 2;
            if (calendar.getMonthOfYear() == 1)
                weekOfMonth = weekOfYear;

            System.out.println("День: " + calendar.getDayOfMonth());
            System.out.println("День недели: " + (calendar.getDayOfWeek() % 7 + 1));
            System.out.println("День месяца: " + calendar.getDayOfMonth());
            System.out.println("День года: " + calendar.getDayOfYear());
//            System.out.println("Неделя месяца: " + (calendar.getWeekOfWeekyear() - calendar.withDayOfMonth(1).getWeekOfWeekyear() + 1));
//            System.out.println("Неделя года: " + calendar.getWeekOfWeekyear());
            System.out.println("Неделя месяца: " + weekOfMonth);
            System.out.println("Неделя года: " + weekOfYear);
            System.out.println("Месяц: " + (calendar.getMonthOfYear() - 1));
            System.out.println("Год: " + calendar.getYear());
            System.out.println("Эра: " + calendar.getEra());
            System.out.println("AM или PM: " + (calendar.getHourOfDay() < 12 ? 0 : 1));
            System.out.println("Часы: " + calendar.getHourOfDay() % 12);
            System.out.println("Часы дня: " + calendar.getHourOfDay());
            System.out.println("Минуты: " + calendar.getMinuteOfHour());
            System.out.println("Секунды: " + calendar.getSecondOfMinute());
            return;
        }
        catch (ParseException e)
        {
            try
            {
                result = dateFormat2.parse(date);
                calendar = new DateTime(result);

                DateTime minYearDate = calendar.dayOfYear().withMinimumValue();
                DateTime minMonthDate = calendar.dayOfMonth().withMinimumValue();
                int weekDis = (minYearDate.getWeekyear() == calendar.getYear()) ? 0 : 1;
                int weekOfYear = calendar.getWeekOfWeekyear() + weekDis;
                if (weekOfYear >= 53)
                    weekOfYear = 1;
                int weekOfMonth = calendar.getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 1;
                if (minMonthDate.getWeekOfWeekyear() >= calendar.getWeekOfWeekyear())
                    weekOfMonth = calendar.minusDays(7).getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 2;
                if (calendar.getMonthOfYear() == 1)
                    weekOfMonth = weekOfYear;

                System.out.println("День: " + calendar.getDayOfMonth());
                System.out.println("День недели: " + (calendar.getDayOfWeek() % 7 + 1));
                System.out.println("День месяца: " + calendar.getDayOfMonth());
                System.out.println("День года: " + calendar.getDayOfYear());
//                System.out.println("Неделя месяца: " + (calendar.getWeekOfWeekyear() - calendar.withDayOfMonth(1).getWeekOfWeekyear() + 1));
//                System.out.println("Неделя года: " + calendar.getWeekOfWeekyear());
                System.out.println("Неделя месяца: " + weekOfMonth);
                System.out.println("Неделя года: " + weekOfYear);
                System.out.println("Месяц: " + (calendar.getMonthOfYear() - 1));
                System.out.println("Год: " + calendar.getYear());
                System.out.println("Эра: " + calendar.getEra());
                return;
            }
            catch (ParseException e1)
            {
                try
                {
                    result = dateFormat3.parse(date);
                    calendar = new DateTime(result);
                    System.out.println("AM или PM: " + (calendar.getHourOfDay() < 12 ? 0 : 1));
                    System.out.println("Часы: " + calendar.getHourOfDay() % 12);
                    System.out.println("Часы дня: " + calendar.getHourOfDay());
                    System.out.println("Минуты: " + calendar.getMinuteOfHour());
                    System.out.println("Секунды: " + calendar.getSecondOfMinute());
                    return;
                }
                catch (ParseException e2)
                {
                }
            }

        }
    }
}
