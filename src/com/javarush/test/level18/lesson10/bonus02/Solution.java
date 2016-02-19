package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

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
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        if(args[0].equals("-c") && args.length == 4){
            File file = new File(fileName);
            if(file.length() > 0){
                BufferedReader fileReader = new BufferedReader(new FileReader(file));
                ArrayList<String> list = new ArrayList<String>();
                String s = null;
                while((s = fileReader.readLine()) != null){
                    list.add(s);
                }
                fileReader.close();
                if(list.size() > 0){
                    int max = -1;
                    for(int i = 0; i < list.size(); i++){
                        if(!(list.get(i).equals(""))){
                            String idString = list.get(i).substring(0, 8);
                            if(!idString.equals("        ")){
                                int space = idString.indexOf(' ');
                                if(space != -1){
                                    idString = idString.substring(0, space);
                                }
                                int id = Integer.parseInt(idString);
                                if(id > max) max = id;
                            }
                        }
                    }
                    max++;
                    if(max == 0) max = 1;
                    String line = String.format("%-8.8s%-30.30s%-8.8s%-4.4s", max, args[1], args[2], args[3]);
                    BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true));
                    fileWriter.newLine();
                    fileWriter.write(line);
                    fileWriter.close();
                }
            }
            else {
                if(args[0].equals("-c") && args.length == 4){
                    BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true));
                    fileWriter.newLine();
                    fileWriter.write(String.format("1       %-30.30s%-8.8s%-4.4s", args[1], args[2], args[3]));
                    fileWriter.close();
                }
            }
        }
    }
}
