package com.javarush.test.level31.lesson08.home02;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/* Исследуем Path
Почитайте про все методы класса Path.
Найдите такой, который создает относительный путь между текущим и переданным путем.
Реализуйте логику метода getDiffBetweenTwoPaths, он должен возвращать относительный путь.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("D:/test/data/firstDir");
        Path path2 = Paths.get("D:/test/data/secondDir/third");
        Path resultPath = getDiffBetweenTwoPaths(path1, path2);
        System.out.println(resultPath);   //expected output '../secondDir/third' or '..\secondDir\third'
    }

    public static Path getDiffBetweenTwoPaths(Path path1, Path path2) {
        return path1.relativize(path2);
    }
}
