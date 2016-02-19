package com.javarush.test.level19.lesson10.bonus02;

/* Свой FileWriter
Реализовать логику FileConsoleWriter
Должен наследоваться от FileWriter
При записи данных в файл, должен дублировать эти данные на консоль
*/

import java.io.*;
import java.util.Arrays;

public class FileConsoleWriter extends FileWriter {
    public FileConsoleWriter(String fileName) throws IOException {
        super(fileName);
    }
    @Override
    public void write(int c) throws IOException {
        Character ch = (char) c;
        System.out.println(ch);
        super.write(c);
    }
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len ; i++) {
            System.out.print(cbuf[i]);
        }
        System.out.println();
        super.write(cbuf, off, len);
    }
    @Override
    public void write(String str, int off, int len) throws IOException {
        System.out.println(str.substring(off, len));
        super.write(str, off, len);
    }
}