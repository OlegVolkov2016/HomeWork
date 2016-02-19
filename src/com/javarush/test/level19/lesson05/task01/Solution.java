package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
Закрыть потоки ввода-вывода.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        FileReader fr = new FileReader(fileName1);
        FileWriter fw = new FileWriter(fileName2);
        boolean even = false;
        while (fr.ready()) {
            if (even) {
                fw.write((byte) fr.read());
                even = false;
            } else {
                fr.read();
                even = true;
            }
        }
        fw.close();
        fr.close();
        reader.close();
    }
}
