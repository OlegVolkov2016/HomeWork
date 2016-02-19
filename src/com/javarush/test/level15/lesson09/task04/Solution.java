package com.javarush.test.level15.lesson09.task04;

/* Статики и исключения
В статическом блоке выбросьте RuntimeException
В результате класс не загрузится, и вы увидите сообщение об ошибке вместо значения переменной B
Exception in thread "main" java.lang.ExceptionInInitializerError
at java.lang.Class.forName0(Native Method)
at java.lang.Class.forName(Class.java:186)
at com.intellij.rt.execution.application.AppMain.main(AppMain.java:113)
Caused by: java.lang.RuntimeException:
at com.javarush.test.level15.lesson09.task04.Solution_1.clinit(Solution_1.java:22)
Hint: Нужно погуглить причину, если получилось следующее:
java: initializer must be able to complete normally
java: unreachable statement
*/

public class Solution {
    public static int A = 0;

    static {
        if (1 > 0)
            throw new RuntimeException();

        //throw an exception here - выбросьте эксепшн тут

    }

    public static int B = 5;

    public static void main(String[] args) {
        System.out.println(B);
    }
}