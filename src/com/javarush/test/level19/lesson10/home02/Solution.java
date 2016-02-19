package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        TreeMap<String, Double> map = new TreeMap<>();
        double max = 0;
        while (reader.ready()) {
            String[] values = reader.readLine().split(" ");
            if (map.containsKey(values[0])) {
                map.put(values[0], map.get(values[0]) + Double.parseDouble(values[1]));
            }
            else {
                map.put(values[0], Double.parseDouble(values[1]));
            }
            if (map.get(values[0]) > max) {
                max = map.get(values[0]);
            }
        }
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            if (pair.getValue() == max) {
                System.out.print(pair.getKey() + " ");
            }
        }
        reader.close();
    }
}
