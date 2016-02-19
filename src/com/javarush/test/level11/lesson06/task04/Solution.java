package com.javarush.test.level11.lesson06.task04;

/* Все мы работники
Написать четыре класса: Employee(сотрудник), Manager(управляющий), Chief(директор) и  Secretary(секретарь).
Унаследовать управляющего, директора и секретаря от сотрудника.
*/

public class Solution
{
    public class Manager extends Employee
    {
        private int salary;
        public Manager(String name, int salary) {
            super(name);
            this.salary = salary;
        }
    }

    public class Chief extends Employee
    {
        private int money;
        public Chief(String name, int money) {
            super(name);
            this.money = money;
        }
    }

    public class Employee
    {
        private String name;
        public Employee(String name) {
            this.name = name;
        }
    }

    public class Secretary extends Employee
    {
        private int age;
        public Secretary(String name, int age) {
            super(name);
            this.age = age;
        }
    }
}
