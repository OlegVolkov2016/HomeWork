package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        try
        {
            String s = reader.readLine();
            int i1 = s.indexOf('?');
            if (i1 >= 0) {
                String[] params = s.substring(i1+1).split("&");
                for (String param : params) {
                    int i2 = param.indexOf('=');
                    String name = param;
                    String value = "";
                    if (i2 >= 0) {
                        name = param.substring(0,i2);
                        value = param.substring(i2+1);
                    }
                    list.add(name);
                    if ("obj".equals(name)) {
                        values.add(value);
                    }
                }
            }
            for (String param : list) {
                System.out.print(param + " ");
            }
            System.out.println();
            for (String param : values) {
                try {
                    double d = Double.parseDouble(param);
                    alert(d);
                } catch (NumberFormatException e) {
                    alert(param);
                }
//                if (param.indexOf('.') >= 0) {
//                    alert(Double.parseDouble(param));
//                }
//                else {
//                    alert(param);
//                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
