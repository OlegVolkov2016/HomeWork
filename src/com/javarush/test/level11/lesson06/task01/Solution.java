package com.javarush.test.level11.lesson06.task01;

/* Лошадь и пегас
Написать два класса: Horse (лошадь) и Pegas (пегас).
Унаследовать пегаса от лошади.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Horse horse  = new Horse("Ann");
        Pegas pegas = new Pegas("Peter", 10);

    }

    public static class Horse
    {
        private String name;
        public Horse(String name) {
            this.name = name;
        }
    }

    public static class Pegas extends Horse
    {
        private int wingsWidth;
        public Pegas(String name, int wingsWidth) {
            super(name);
            this.wingsWidth = wingsWidth;
        }
    }
}
