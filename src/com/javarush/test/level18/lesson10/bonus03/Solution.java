package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String outputFileName = reader.readLine();
            reader.close();
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(outputFileName)));
            ArrayList<String> inputStrings = new ArrayList<String>();
            while (fileReader.ready())
                inputStrings.add(fileReader.readLine());
            fileReader.close();
            if (args.length >= 2)
            {
                if ("-u".equals(args[0]) && args.length >= 5)
                {
                    String outputString = getId(args) + getProductName(args) + getPrice(args) + getQuantity(args);
                    int idToUpdate = getStringIndexById(args, inputStrings);
                    if (idToUpdate != -1)
                        inputStrings.set(idToUpdate, outputString);
                }
                if ("-d".equals(args[0]) && args.length == 2)
                {
                    int idToDelete = getStringIndexById(args, inputStrings);
                    if (idToDelete != -1)
                        inputStrings.remove(idToDelete);
                }
                writeFile(inputStrings, outputFileName);
            }
        }
        catch (IOException e)
        {
        }
    }

    private static void writeFile(ArrayList<String> inputStrings, String outputFileName)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, false));
            if (inputStrings.size() == 0)
            {
                ;
            } else if (inputStrings.size() == 1)
                writer.write(inputStrings.get(0));
            else
            {
                for (int i = 0; i < inputStrings.size() - 1; i++)
                {
                    writer.write(inputStrings.get(i));
                    writer.write(System.getProperty("line.separator"));
                }
                writer.write(inputStrings.get(inputStrings.size() - 1));
            }
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
        }
    }

    private static int getStringIndexById(String[] args, ArrayList<String> s)
    {
        // returns the index in the ArrayList
        String x = getId(args);
        int id = -1;
        for (int i = 0; i < s.size(); i++)
        {
            String argument = s.get(i).substring(0,8);
            if (argument.equals(x))
                id = i;
        }
        return id;
    }

    private static String getId(String[] args)
    {
        // returns the formatted id
        String result = args[1]; // here lives the original id
        result = String.format("%-8.8s", result);
        return result;
    }

    private static String getProductName(String[] args)
    {
        // returns the formatted String of a product name
        int length = args.length;
        String result = "";
        for (int i = 2; i < length - 2; i++)
            result = result + args[i] + " ";
        result = String.format("%-30.30s", result);
        return result;
    }

    private static String getPrice(String[] args)
    {
        // returns the formatted price
        String result = args[args.length - 2];
        result = String.format("%-8.8s", result);
        return result;
    }

    private static String getQuantity(String[] args)
    {
        // returns the formatted quantity
        String result = args[args.length - 1];
        result = String.format("%-4.4s", result);
        return result;
    }

}