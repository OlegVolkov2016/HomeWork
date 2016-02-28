package com.javarush.test.level31.lesson06.home01;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException
    {
        if (args.length >= 2) {
            String pathToZip = args[1];
//            String pathToZip = "I:\\I.zip";
            String pathToFile = args[0];
//            String pathToFile = "I:\\3.txt";
            //читаем архив в мапу
            Map<String, ByteArrayOutputStream> entryMap = new HashMap<>();
            try (ZipInputStream zis = new ZipInputStream(new FileInputStream(pathToZip))) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    if(entry.isDirectory()){
                        entryMap.put(entry.getName(), null);
                    } else{
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int count;
                        while ((count = zis.read(buffer)) != -1) {
                            baos.write(buffer, 0, count);
                        }
                        entryMap.put(entry.getName(), baos);
                        baos.close();
                    }
                    zis.closeEntry();
                }
            }
            //вычисляем простое имя файла
            String shortFileName = pathToFile.substring(pathToFile.lastIndexOf("\\") + 1, pathToFile.length());
            //записываем файл в byteArrayOutputStream
            ByteArrayOutputStream byteArrayOutputStream = null;
            try (FileInputStream fileInputStream = new FileInputStream(pathToFile)) {
                int length = fileInputStream.available();
                byte[] bytes = new byte[length];
                fileInputStream.read(bytes);
                byteArrayOutputStream = new ByteArrayOutputStream(length);
                byteArrayOutputStream.write(bytes);
            }
            //записываем мапу в файл
            try(FileOutputStream zipWriteFile = new FileOutputStream(pathToZip);
                ZipOutputStream outZip = new ZipOutputStream(zipWriteFile)) {
                for (Map.Entry<String, ByteArrayOutputStream> pair : entryMap.entrySet()) {
                    if (!pair.getKey().equals(shortFileName)) {
                        outZip.putNextEntry(new ZipEntry(pair.getKey()));
                        outZip.write(pair.getValue().toByteArray());
                        outZip.closeEntry();
                    } else {
                        outZip.putNextEntry(new ZipEntry("new\\" + shortFileName));
                        outZip.write(byteArrayOutputStream.toByteArray());
                        outZip.closeEntry();
                    }
                }
            }
            byteArrayOutputStream.close();
        }
    }
}
