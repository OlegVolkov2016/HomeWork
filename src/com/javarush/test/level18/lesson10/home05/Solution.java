package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader r=new BufferedReader(new InputStreamReader(System.in));
        String f1=r.readLine(),f2=r.readLine();
        Scanner sc=new Scanner(new File(f1));
        FileWriter fl = new FileWriter(f2);
        while (sc.hasNext()){
            String[] rr=sc.nextLine().split(" ");
            for (int i=0;i<rr.length;i++)
            {
                fl.write(String.valueOf(Math.round(Double.parseDouble(rr[i])))+" ");
            }}
        sc.close();
        fl.close();
        r.close();
    }
}
