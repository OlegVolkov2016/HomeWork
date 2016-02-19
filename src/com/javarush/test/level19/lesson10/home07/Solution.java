package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader file = new BufferedReader(new FileReader(args[0]));
        FileWriter writer = new FileWriter(args[1]);
        String[] values;
        String result = "";
        while (file.ready()) {
            values = file.readLine().split(" ");
            for (String s : values) {
                if (s.length() > 6) {
                    result += s + ",";
                }
            }
        }
        result = result.substring(0, result.length() - 1);
        writer.write(result);
        writer.close();
        file.close();
    }
}
