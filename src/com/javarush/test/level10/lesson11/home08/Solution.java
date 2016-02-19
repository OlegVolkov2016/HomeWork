package com.javarush.test.level10.lesson11.home08;

import java.util.ArrayList;

/* Массив списков строк
Создать массив, элементами которого будут списки строк. Заполнить массив любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<String>[] arrayOfStringList =  createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList()
    {
        ArrayList<String>[] arrayOfStringList = new ArrayList[2];
        arrayOfStringList[0] = new ArrayList<>();
        arrayOfStringList[1] = new ArrayList<>();
        arrayOfStringList[0].add("gsdadgj");
        arrayOfStringList[0].add("daskadl");
        arrayOfStringList[1].add("ogrsd;f");
        arrayOfStringList[1].add("adwudawiaw");
        arrayOfStringList[1].add("adwwoda");
        return arrayOfStringList;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList)
    {
        for (ArrayList<String> list: arrayOfStringList)
        {
            for (String s : list)
            {
                System.out.println(s);
            }
        }
    }
}