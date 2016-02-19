package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws IOException
    {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        InputStream inputStream = new FileInputStream(name);
        try {
            load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream.close();
        reader.close();
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties props = new Properties();
        for (Map.Entry<String, String> pair : properties.entrySet()) {
            props.setProperty(pair.getKey(), pair.getValue());
        }
        props.store(outputStream, null);
        outputStream.flush();
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties props = new Properties();
        props.load(inputStream);
        Set<String> keys = props.stringPropertyNames();
        properties.clear();
        for (String key : keys) {
            properties.put(key, props.getProperty(key));
        }
    }
}
