package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        DataInputStream file = new DataInputStream(new FileInputStream(s));
        ArrayList<Integer> list = new ArrayList<>();
        while(file.available() > 0) {
            int i = Integer.parseInt(file.readLine());
            if (i % 2 == 0) {
                list.add(i);
            }
        }
        file.close();
        reader.close();
        Integer[] array = new Integer[list.size()];
        list.toArray(array);
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    Integer x = array[i];
                    array[i] = array[j];
                    array[j] = x;
                }
            }

        }
        for (Integer i : array) {
            System.out.println(i);
        }
    }
}
