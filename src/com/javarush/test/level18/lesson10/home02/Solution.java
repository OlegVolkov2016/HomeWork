package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        try
        {
            FileInputStream fis = new FileInputStream(args[0]);
            int letters = 0;
            int spaces = 0;
            while (fis.available() > 0) {
                char c = (char) fis.read();
                if (c == ' ') {
                    spaces++;
                }
                letters++;
            }
            fis.close();
            System.out.printf("%.2f", spaces * 1.0 / letters * 100);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
