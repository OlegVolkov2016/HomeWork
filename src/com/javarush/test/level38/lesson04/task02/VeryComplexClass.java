package com.javarush.test.level38.lesson04.task02;

/* Непроверяемые исключения (unchecked exception)
Напиши реализацию метода methodThrowsClassCastException(). Он должен
всегда кидать непроверяемое исключение ClassCastException.

Напиши реализацию метода methodThrowsNullPointerException(). Он должен
всегда кидать непроверяемое исключение NullPointerException.

Кинуть исключение (throw) явно нельзя.
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object s = "A";
        Integer i = (Integer) s;
    }

    public void methodThrowsNullPointerException() {
        //напишите тут ваш код
        String s = null;
        s.length();
    }
}
