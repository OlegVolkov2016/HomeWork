package com.javarush.test.level11.lesson06.task05;

/* ИТ-компания
Написать девять классов: Worker(сотрудник), Clerk (клерк), IT (ИТ-специалист), Programmer(программист), ProjectManager(менеджер проекта), CTO(технический директор), HR(рекрутер), OfficeManager(офис-менеджер), Cleaner (уборщик).
Унаследовать программиста, менеджера проекта и технического директора от ИТ-специалиста.
Унаследовать рекрутера, уборщика и офис-менеджера от клерка.
Унаследовать клерка и ИТ-специалиста от сотрудника.
*/

public class Solution
{
    public static void main(String[] args)
    {
    }

    public class Worker
    {
        private String name;
        public Worker(String name) {
            this.name = name;
        }
    }

    public class Clerk extends Worker
    {
        private int age;
        public Clerk(String name, int age) {
            super(name);
            this.age = age;
        }
    }

    public class IT extends Worker
    {
        private int experience;
        public IT(String name, int experience) {
            super(name);
            this.experience = experience;
        }
    }

    public class Programmer extends IT
    {
        private String language;
        public Programmer(String name, int experience, String language) {
            super(name, experience);
            this.language = language;
        }
    }

    public class ProjectManager extends IT
    {
        private String project;
        public ProjectManager(String name, int experience, String project) {
            super(name, experience);
            this.project = project;
        }
    }

    public class CTO extends IT
    {
        private String unit;
        public CTO(String name, int experience, String unit) {
            super(name, experience);
            this.unit = unit;
        }
    }

    public class OfficeManager extends Clerk
    {
        private int hours;
        public OfficeManager(String name, int age, int hours) {
            super(name, age);
            this.hours = hours;
        }
    }

    public class HR extends Clerk
    {
        private int workers;
        public HR(String name, int age, int workers) {
            super(name, age);
            this.workers = workers;
        }
    }

    public class Cleaner extends Clerk
    {
        private int floor;
        public Cleaner(String name, int age, int floor) {
            super(name, age);
            this.floor = floor;
        }
    }
}
