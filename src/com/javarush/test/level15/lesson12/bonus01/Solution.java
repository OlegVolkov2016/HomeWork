package com.javarush.test.level15.lesson12.bonus01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Осваивание статического блока
1. В отдельных файлах создать классы Plane и Helicopter, реализующие интерфейс Flyable.
2. Класс Plane должен иметь 1 конструктор с параметром int - количество перевозимых пассажиров.
3. В статическом методе reset класса Solution_1:
3.1. Считать с консоли параметр типа String. Параметр может быть "plane" или "helicopter".
3.2. Если параметр равен "helicopter", то статическому объекту Flyable result присвоить объект класса Helicopter.
3.3. Если параметр равен "plane", то считать второй параметр типа int, статическому объекту Flyable result присвоить объект класса Plane.
4. В статическом блоке инициализировать Flyable result вызвав метод reset.
5. Закрыть поток ввода методом close().
*/

public class Solution {
    static {
        reset();
    }

    public static Flyable result;

    public static void reset() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String s = reader.readLine();
            if ("helicopter".equals(s)) {
                result = new Helicopter();
            }
            else if ("plane".equals(s)) {
                int i = Integer.parseInt(reader.readLine());
                result = new Plane(i);
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
