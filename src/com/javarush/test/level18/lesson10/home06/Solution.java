package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources

Пример вывода:
, 19
- 7
f 361
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        int[] ch  = new int[256];
        char[] chst = new char[1024];
        while (br.ready()) {
            int chstLen = br.read(chst);
            for (int i=0; i<chstLen; i++)
                ch[chst[i]]++;
        }
        for (int i=0; i<ch.length; i++)
            if (ch[i] != 0)
                System.out.println((char)i + " " + ch[i]);
        br.close();
    }
}
