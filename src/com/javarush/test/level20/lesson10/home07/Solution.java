package com.javarush.test.level20.lesson10.home07;

import java.io.*;

/* Переопределение сериализации в потоке
Сериализация/десериализация Solution_1 не работает.
Исправьте ошибки не меняя сигнатуры методов и класса.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать экземпляр класса Solution_1
2) записать в него данные  - writeObject
3) сериализовать класс Solution_1  - writeObject(ObjectOutputStream out)
4) десериализовать, получаем новый объект
5) записать в новый объект данные - writeObject
6) проверить, что в файле есть данные из п.2 и п.5
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    private String filename;

    public Solution(String fileName) throws FileNotFoundException {
        this.filename = fileName;
        this.stream = new FileOutputStream(this.filename);
    }

    public static void main(String[] args) {
        try {
            Solution solution = new Solution("test");
            solution.writeObject("Строка");
            FileOutputStream out = new FileOutputStream("a");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(solution);
            objectOutputStream.close();
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("a"));
            Solution newSolution = (Solution) objectInputStream.readObject();
            objectInputStream.close();
            newSolution.writeObject("акортС");
            BufferedReader in = new BufferedReader(new FileReader(newSolution.filename));
            while (in.ready()) {
                System.out.println(in.readLine());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.flush();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(this.filename, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }
}
