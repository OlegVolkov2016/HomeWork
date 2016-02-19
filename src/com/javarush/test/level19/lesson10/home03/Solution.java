package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException
    {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        StringBuilder name;
        Date birthday;
        while (reader.ready()) {
            name = new StringBuilder();
            String[] values = reader.readLine().split(" ");
            for (int i = 0; i < values.length - 2; i++) {
                try {
                    Integer.parseInt(values[i]);
                    name.delete(name.length() - 1, name.length());
                    if (values[i].length() == 1) {
                        values[i] = "0" + values[i];
                    }
                    if (values[i + 1].length() == 1) {
                        values[i + 1] = "0" + values[i + 1];
                    }
                    birthday = new SimpleDateFormat("ddMMyyyy").parse(values[i] + values[i + 1] + values[i + 2]);
                    PEOPLE.add(new Person(name.toString(),birthday));

                } catch (NumberFormatException e) {
                    name.append(values[i] + " ");
                }
            }
        }
        reader.close();

    }


}
