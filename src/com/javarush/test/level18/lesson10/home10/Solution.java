package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            HashMap<Integer, String> fileNames = new HashMap<>();
            String fileName;
            while (!(fileName = reader.readLine()).equals("end")) {
                int index = Integer.parseInt(fileName.substring(fileName.lastIndexOf('.')+5));
                fileNames.put(index,fileName);
            }
            String resultName = fileNames.get(1);
            resultName = resultName.substring(0,resultName.lastIndexOf('.'));
            FileOutputStream fos = new FileOutputStream(resultName);
            FileInputStream fis;
            byte[] buffer;
            for (int i = 1; i <= fileNames.size(); i++) {
                fis = new FileInputStream(fileNames.get(i));
                buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                fos.write(buffer);
            }
            fos.close();
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
