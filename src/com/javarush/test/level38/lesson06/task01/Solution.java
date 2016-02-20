package com.javarush.test.level38.lesson06.task01;

/* Улучшения в Java 7 (try-with-resources)
Перепиши реализации методов класса Solution.
Используй нововведения, касающиеся обработки исключений, которые были добавлены в Java 7.
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public void printFile1() throws IOException {
//        FileInputStream fileInputStream = null;

        try (FileInputStream fileInputStream = new FileInputStream("file.txt")) {
//            fileInputStream = new FileInputStream("file.txt");

            int data = fileInputStream.read();
            while (data != -1) {
                System.out.println(data);
                data = fileInputStream.read();
            }
        }
//        finally {
//            if (fileInputStream != null) {
//                fileInputStream.close();
//            }
//        }
    }

    public void printFile2() throws IOException {
//        FileInputStream fileInputStream = null;
//        BufferedInputStream bufferedInputStream = null;
        try (FileInputStream fileInputStream = new FileInputStream("file.txt");
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
//            fileInputStream = new FileInputStream("file.txt");
//            bufferedInputStream = new BufferedInputStream(fileInputStream);

            int data = bufferedInputStream.read();
            while (data != -1) {
                System.out.println(data);
                data = bufferedInputStream.read();
            }
        }
//        finally {
//            if (fileInputStream != null) {
//                fileInputStream.close();
//            }
//
//            if (bufferedInputStream != null) {
//                bufferedInputStream.close();
//            }
//        }
    }
}