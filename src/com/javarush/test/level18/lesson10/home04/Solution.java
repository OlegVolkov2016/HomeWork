package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName1 = reader.readLine();
            String fileName2 = reader.readLine();
            FileInputStream fis1 = new FileInputStream(fileName1);
            FileInputStream fis2 = new FileInputStream(fileName2);
            byte[] buffer1 = new byte[fis1.available()];
            byte[] buffer2 = new byte[fis2.available()];
            fis1.read(buffer1);
            fis2.read(buffer2);
            fis1.close();
            fis2.close();
            FileOutputStream fis3 = new FileOutputStream(fileName1);
            fis3.write(buffer2);
            fis3.write(buffer1);
            fis3.close();
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
