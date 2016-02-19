package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        while (!(fileName = reader.readLine()).equals("exit")) {
            new ReadThread(fileName).start();
        }
        reader.close();

    }

    public static class ReadThread extends Thread {
        private String fileName;
        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run()
        {
            try
            {
                HashMap<Integer,Integer> bytes = new HashMap<>();
                int max = 1;
                FileInputStream fis = new FileInputStream(fileName);
                while (fis.available() > 0) {
                    int i = fis.read();
                    if (bytes.containsKey(i)) {
                        int count = bytes.get(i) + 1;
                        bytes.put(i, count);
                        if (count > max) {
                            max = count;
                        }
                    } else {
                        bytes.put(i, 1);
                    }
                }
                fis.close();
                for (Map.Entry<Integer, Integer> entry : bytes.entrySet()) {
                    if (entry.getValue() == max) {
                        synchronized (resultMap) {
                            resultMap.put(fileName, entry.getKey());
                        }
                        break;
                    }
                }
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }
}
