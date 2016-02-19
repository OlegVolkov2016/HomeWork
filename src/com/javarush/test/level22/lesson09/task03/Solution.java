package com.javarush.test.level22.lesson09.task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file = new BufferedReader(new FileReader(reader.readLine()));
        String s = "";
        while(file.ready())
        {
            s = s + file.readLine();
        }
        String[] words = s.split(" ");
        reader.close();
        file.close();
        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }
    public static StringBuilder getLine(String... words)
    {
        if (words == null || words.length == 0)
            return new StringBuilder();
        if ("".equals(words[0]) || words.length == 1)
            return new StringBuilder(words[0]);
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(words));
        int count = 0;
        StringBuilder s = null;
        while(count!=list.size())
        {
            count = 1;
            Collections.shuffle(list);
            s = new StringBuilder(list.get(0));
            for(int i = 0; i < list.size()-1; i++)
            {
                if(list.get(i).toLowerCase().charAt(list.get(i).length()-1)==list.get(i+1).toLowerCase().charAt(0))
                {
                    s.append(" " + list.get(i+1));
                    count++;
                }
            }
        }
        return s;
    }
}