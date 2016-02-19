package com.javarush.test.level06.lesson11.home05;

/* Есть новые идеи? Подумаем...
1. В классе Solution_1 создать public статический класс Idea
2. В классе Idea создать метод public String getDescription(), который будет возвращать любой непустой текст
3. В классе Solution_1 создайте статический метод public void printIdea(Idea idea), который будет выводить
на экран описание идеи - это то, что возвращает метод getDescription
*/

public class Solution
{
    public static void main(String[] args)
    {
        printIdea(new Idea());
    }

    public static class Idea {
        public String getDescription() {
            return "Test";
        }
    }

    public static void printIdea(Idea idea) {
        System.out.println(idea.getDescription());
    }
}
