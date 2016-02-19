package com.javarush.test.level25.lesson09.task03;

import java.util.Stack;

/* Живем своим умом
В классе Solution_1 реализуйте интерфейс UncaughtExceptionHandler, который должен:
1. прервать нить, которая бросила исключение.
2. вывести в консоль стек исключений начиная с самого вложенного.
Пример исключения: new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")))
Пример вывода:
java.lang.IllegalAccessException: GHI
java.lang.RuntimeException: DEF
java.lang.Exception: ABC
*/
public class Solution implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        Stack<Throwable> exceptions = new Stack<>();
        exceptions.push(e);
        Throwable throwable = e.getCause();
        while (throwable != null) {
            exceptions.push(throwable);
            throwable = throwable.getCause();
        }
        while (!exceptions.empty()) {
            Throwable currentException = exceptions.pop();
            System.out.println(currentException.getClass().getName() + ": " +currentException.getMessage());
        }
    }
}
