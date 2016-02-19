package com.javarush.test.level11.lesson11.home05;

/* От школьника к рабству
Написать четыре класса: Schoolboy(школьник), Student(студент), Worker(Сотрудник), Slave (Раб)
Унаследовать студента от школьника, сотрудника от студента, раба от сотрудника.
*/

public class Solution
{
    public static void main(String[] args)
    {
    }

    public class Schoolboy
    {
        private String name;
        public Schoolboy(String name) {
            this.name = name;
        }
    }

    public class Student extends Schoolboy
    {
        private int age;
        public Student(String name, int age) {
            super(name);
            this.age = age;
        }
    }

    public class Worker extends Student
    {
        private int salary;
        public Worker(String name, int age, int salary) {
            super(name, age);
            this.salary = salary;
        }
    }

    public class Slave extends Worker
    {
        private int strength;
        public Slave(String name, int age, int salary, int strength) {
            super(name, age, salary);
            this.strength = strength;
        }
    }

}
