package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        Scanner scanner = new Scanner(new FileReader(fileName1));
        FileWriter fw = new FileWriter(fileName2, true);
        int count = 0;
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(" ");
            for (String value : line) {
                try {
                    int i = Integer.parseInt(value);
                    fw.write((i + " ").toCharArray());
                } catch (NumberFormatException e) {

                }
            }
        }
        fw.close();
        scanner.close();
        reader.close();
    }
}
