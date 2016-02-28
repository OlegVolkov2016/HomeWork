package com.javarush.test.level40.lesson10.task03;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

/* В какой день недели день рождения?
Реализуй метод weekDayOfBirthday.
Метод должен возвращать день недели на итальянском языке, в который будет день рождения в определенном году.
Пример формата дат смотри в методе main.

Пример:
1) Для "30.05.1984" и "2015" метод должен вернуть: sabato
2) Для "1.12.2015" и "2016" метод должен вернуть: giovedì

Выполни задание, используя библиотеку Joda Time версии 2.9.1
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(weekDayOfBirthday("30.05.1984", "2015"));
        System.out.println(weekDayOfBirthday("1.12.2015", "2016"));
    }

    public static String weekDayOfBirthday(String birthday, String year) {
        //напишите тут ваш код
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
        DateTime date = dateTimeFormatter.parseDateTime(birthday);
        DateTimeFormatter yearFormatter = DateTimeFormat.forPattern("yyyy");
        int birthdayYear = yearFormatter.parseDateTime(year).getYear();
        date = date.plusYears(birthdayYear - date.getYear());
        DateTimeFormatter dayFormatter = DateTimeFormat.forPattern("EEEE");
        return date.toString("EEEE", Locale.ITALY);
    }
}
