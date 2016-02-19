package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        FileOutputStream file = new FileOutputStream(s);
        do {
            s = reader.readLine();
            for (int i = 0; i < s.length(); i++) {
                file.write(s.charAt(i));
            }
//            file.write('\r');
            file.write('\n');
        }
        while (!s.equals("exit"));
        file.close();
        reader.close();
    }
}
