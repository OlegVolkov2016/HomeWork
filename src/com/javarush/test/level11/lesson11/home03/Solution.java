package com.javarush.test.level11.lesson11.home03;

/* Эволюция
Написать четыре класса: Fish(Рыбы), Animal(Животные), Ape(Обезьяны), Human (Человек).
Унаследовать животных от рыб, обезьян от животных и человека от обезьян.
*/

public class Solution
{
    public static void main(String[] args)
    {
    }

    public class Fish
    {
        private String name;
        public Fish(String name) {
            this.name = name;
        }
    }

    public class Animal extends Fish
    {
        private int weight;
        public Animal(String name, int weight) {
            super(name);
            this.weight = weight;
        }
    }

    public class Ape extends Animal
    {
        private int height;
        public Ape(String name, int weight, int height) {
            super(name, weight);
            this.height = height;
        }
    }

    public class Human extends Ape
    {
        private int IQ;
        public Human(String name, int weight, int height, int IQ) {
            super(name, weight, height);
            this.IQ = IQ;
        }
    }

}
