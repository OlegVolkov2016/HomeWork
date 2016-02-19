package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        FileReader fr = new FileReader(fileName1);
        FileWriter fw = new FileWriter(fileName2);
        while (fr.ready()) {
            char c = (char) fr.read();
            if (c == '.') {
                fw.write('!');
            } else {
                fw.write(c);
            }
        }
        fw.close();
        fr.close();
        reader.close();
    }
}
