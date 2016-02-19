package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        Scanner scanner = new Scanner(new FileReader(fileName1));
        int count = 0;
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("[\\p{P} \\t\\n\\r]");
            for (String word : line) {
                if ("world".equals(word)) {
                    count++;
                }
            }
        }
        System.out.println(count);
        scanner.close();
        reader.close();
    }
}
