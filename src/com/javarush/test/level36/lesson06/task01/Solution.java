package com.javarush.test.level36.lesson06.task01;

import java.util.Collection;
import java.util.Collections;

/* Найти класс по описанию
1. Реализует интерфейс List
2. Является приватным статическим классом внутри популярного утилитного класса
3. Доступ по индексу запрещен - кидается исключение IndexOutOfBoundsException
4. Используйте рефлекшн, чтобы добраться до искомого класса
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class[] classes = Collections.class.getDeclaredClasses();
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].getSimpleName().equals("EmptyList")) {
                return classes[i];
            }
        }
        return null;
    }
}
