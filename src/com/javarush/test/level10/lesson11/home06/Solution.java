package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        String firstname;
        String lastname;
        boolean sex;
        int age;
        int height;
        int weight;

        Human(String firstname) {
            this.firstname = firstname;
        }

        Human(String firstname, String lastname) {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        Human(String firstname, String lastname, boolean sex) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.sex = sex;
        }

        Human(String firstname, String lastname, boolean sex, int age) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.sex = sex;
            this.age = age;
        }

        Human(String firstname, String lastname, boolean sex, int age, int height) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.sex = sex;
            this.age = age;
            this.height = height;
        }

        Human(String firstname, String lastname, boolean sex, int age, int height, int weight) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.sex = sex;
            this.age = age;
            this.height = height;
            this.weight = weight;
        }

        Human(String firstname, String lastname, int age) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.age = age;
        }

        Human(String firstname, String lastname, int age, int height) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.age = age;
            this.height = height;
        }

        Human(String firstname, String lastname, int age, int height, int weight) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.age = age;
            this.height = height;
            this.weight = weight;
        }

        Human(Human human) {
            this.firstname = human.firstname;
            this.lastname = human.lastname;
            this.sex = human.sex;
            this.age = human.age;
            this.height = human.height;
            this.weight = human.weight;
        }
    }
}
