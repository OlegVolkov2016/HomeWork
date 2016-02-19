package com.javarush.test.level11.lesson06.task03;

/* В гостях у бабушки
Написать шесть классов: Animal (животное), Cow(корова) и Pig(свинья), Sheep(овца), Bull(бык), Goat(козел).
Унаследовать корову, свинью, овцу, быка и козла от животного.
*/

public class Solution
{
    public static void main(String[] args)
    {
    }

    public class Animal
    {
        private String name;
        public Animal(String name) {
            this.name = name;
        }
    }

    public class Cow extends Animal
    {
        private int weight;
        public Cow(String name, int weight) {
            super(name);
            this.weight = weight;
        }
    }

    public class Pig extends Animal
    {
        private int height;
        public Pig(String name, int height) {
            super(name);
            this.height = height;
        }
    }

    public class Sheep extends Animal
    {
        private int age;
        public Sheep(String name, int age) {
            super(name);
            this.age = age;
        }
    }

    public class Bull extends Animal
    {
        private int speed;
        public Bull(String name, int speed) {
            super(name);
            this.speed = speed;
        }
    }

    public class Goat extends Animal
    {
        private int voice;
        public Goat(String name, int voice) {
            super(name);
            this.voice = voice;
        }
    }

}
