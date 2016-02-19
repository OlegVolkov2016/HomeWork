package com.javarush.test.level11.lesson06.task02;

/* Домашние животные
Написать три класса: Pet (домашнее животное), Cat(кот) и Dog(собака).
Унаследовать кота и собаку от животного.
*/

public class Solution
{
    public static void main(String[] args)
    {
    }

    public class Pet
    {
        private String name;
        public Pet(String name) {
            this.name = name;
        }
    }

    public class Cat extends Pet
    {
        private int weight;
        public Cat(String name, int weight) {
            super(name);
            this.weight = weight;
        }
    }

    public class Dog extends Pet
    {
        private int height;
        public Dog(String name, int height) {
            super(name);
            this.height = height;
        }
    }
}
