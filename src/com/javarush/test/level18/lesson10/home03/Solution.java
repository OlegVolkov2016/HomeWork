package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
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
            String fileName3 = reader.readLine();
            FileOutputStream fis1 = new FileOutputStream(fileName1);
            FileInputStream fis2 = new FileInputStream(fileName2);
            FileInputStream fis3 = new FileInputStream(fileName3);
            byte[] buffer = new byte[fis2.available()];
            fis2.read(buffer);
            fis1.write(buffer);
            buffer = new byte[fis3.available()];
            fis3.read(buffer);
            fis1.write(buffer);
            fis1.close();
            fis2.close();
            fis3.close();
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
