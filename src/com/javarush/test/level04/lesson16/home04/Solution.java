package com.javarush.test.level04.lesson16.home04;

import java.io.*;
import java.util.Scanner;

/* Меня зовут 'Вася'...
Ввести с клавиатуры строку name.
Ввести с клавиатуры дату рождения (три числа): y, m, d.
Вывести на экран текст:
«Меня зовут name
Я родился d.m.y»
Пример:
Меня зовут Вася
Я родился 15.2.1988
*/

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int y = scanner.nextInt();
        int m = scanner.nextInt();
        int d = scanner.nextInt();
        System.out.println("Меня зовут " + name);
        System.out.println("Я родился " + d + "." + m + "." + y);
    }
}
