package com.javarush.test.level19.lesson03.task04;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner scanner;

        public PersonScannerAdapter(Scanner scanner)
        {
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException
        {
            String[] personData = scanner.nextLine().split(" ");
            if (personData.length >= 6) {
                try
                {
                    return new Person(personData[1], personData[2], personData[0], new SimpleDateFormat("ddMMyyyy").parse(personData[3]
                            + personData[4] + personData[5]));
                }
                catch (ParseException e)
                {
                    return null;
                }
            } else {
                return null;
            }
        }

        @Override
        public void close() throws IOException
        {
            scanner.close();
        }
    }
}
