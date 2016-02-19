package com.javarush.test.level04.lesson16.home02;

import java.io.*;
import java.util.Scanner;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int mid = a;
        if ((a < b) && (a > c)) {
            mid = a;
        }
        else if ((a < c) && (a > b)) {
            mid = a;
        }
        else if ((b < c) && (b > a)) {
            mid = b;
        }
        else if ((b < a) && (b > c)) {
            mid = b;
        }
        else if ((c < a) && (c > b)) {
            mid = c;
        }
        else if ((c < b) && (c > a)) {
            mid = c;
        }
        System.out.println(mid);
    }
}
