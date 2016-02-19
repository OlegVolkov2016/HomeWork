package com.javarush.test.level25.lesson09.task01;

/* Поживем - увидим
Все исключения, которые возникают в процессе работы нити Solution_1, должны быть обработаны одним из вариантов:
1. Если это Error, то вывести в консоль "Нельзя дальше работать"
2. Если это Exception, то вывести в консоль "Надо обработать"
3. Если это Throwable, то вывести в консоль "ХЗ"
Реализуйте эту логику.
*/
public class Solution extends Thread {
    public Solution() {
        setUncaughtExceptionHandler(new UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {
                if (e instanceof Error)
                    System.out.println("Нельзя дальше работать");
                else if (e instanceof Exception)
                    System.out.println("Надо обработать");
                else
                    System.out.println("ХЗ");
            }
        });
    }
}
