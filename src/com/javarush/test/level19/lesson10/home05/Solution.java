package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader file = new BufferedReader(new FileReader(args[0]));
        FileWriter writer = new FileWriter(args[1]);
        String[] values;
        boolean match;
        while (file.ready()) {
            values = file.readLine().split(" ");
            for (String s : values) {
                match = false;
                for (int i = 0; i < s.length(); i++) {
                    if ((s.charAt(i) >= '0') && (s.charAt(i) <= '9')) {
                        match = true;
                        break;
                    }
                }
                if (match) {
                    writer.write(s + " ");
                }
            }
        }
        writer.close();
        file.close();
    }
}
