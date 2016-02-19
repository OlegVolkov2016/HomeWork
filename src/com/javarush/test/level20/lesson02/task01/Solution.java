package com.javarush.test.level20.lesson02.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

/* Читаем и пишем в файл: Human
Реализуйте логику записи в файл и чтения из файла для класса Human
Поле name в классе Human не может быть пустым
В файле your_file_name.tmp может быть несколько объектов Human
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            Human sidorov = new Human("Sidorov");
            sidorov.save(outputStream);
            outputStream.flush();

            Human empty = new Human();
            empty.save(outputStream);
            outputStream.flush();

            Human petrov = new Human("Petrov", new Asset("home"), new Asset("dog"));
            petrov.assets.get(0).setPrice(10.8);
            petrov.assets.get(1).setPrice(0.58);
            petrov.save(outputStream);
            outputStream.flush();

            Human somePerson1 = new Human();
            somePerson1.load(inputStream);

            Human somePerson2 = new Human();
            somePerson2.load(inputStream);

            Human somePerson3 = new Human();
            somePerson3.load(inputStream);

            if (ivanov.equals(somePerson1))
                System.out.println("true");

            if (petrov.equals(somePerson3))
                System.out.println("true");

            inputStream.close();
            outputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter writer = new PrintWriter(outputStream);
            if (name != null)
            {
                writer.println(name);
                writer.println(assets.size());
                for (Asset s : assets){
                    writer.println(s.getName());
                    writer.println(s.getPrice());
                }
            }
            writer.flush();
            writer.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            name = reader.readLine();
            int size = Integer.parseInt(reader.readLine());
            for (int i = 0; i < size; i++){
                Asset asset = new Asset(reader.readLine());
                asset.setPrice(Double.parseDouble(reader.readLine()));
                assets.add(asset);
            }
        }

        public static String readLine(InputStream inputStream) throws IOException {
            StringBuilder sb = new StringBuilder();
            while (inputStream.available() > 0) {
                int c = inputStream.read();
                if ((char) c == '\r')
                    if ((char) inputStream.read() == '\n')
                        break;
                sb.append((char) c);
            }
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Human human = (Human) o;
            if (!name.equals(human.name))
                return false;
            if (assets.size() != human.assets.size())
                return false;
            for (int i = 0; i < assets.size(); i++) {
                if (!assets.get(i).getName().equals(human.assets.get(i).getName()))
                    return false;
                if (assets.get(i).getPrice() != human.assets.get(i).getPrice())
                    return false;
            }
            return true;
        }
    }
}
