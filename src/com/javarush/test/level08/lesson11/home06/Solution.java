package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        Human h1 = new Human("Ann",false,10,new ArrayList<Human>());
        Human h2 = new Human("Bob",true,11,new ArrayList<Human>());
        Human h3 = new Human("Carl",true,12,new ArrayList<Human>());
        ArrayList<Human> list = new ArrayList<Human>();
        list.add(h1);
        list.add(h2);
        list.add(h3);
        Human h4 = new Human("Diana",false,30,new ArrayList<Human>(list));
        Human h5 = new Human("Erl",true,31,new ArrayList<Human>(list));
        list = new ArrayList<Human>();
        list.add(h4);
        Human h6 = new Human("Kate",false,70,new ArrayList<Human>(list));
        Human h7 = new Human("Leon",true,71,new ArrayList<Human>(list));
        list = new ArrayList<Human>();
        list.add(h5);
        Human h8 = new Human("Madlen",false,80,new ArrayList<Human>(list));
        Human h9 = new Human("Nick",true,81,new ArrayList<Human>(list));
        System.out.println(h9);
        System.out.println(h7);
        System.out.println(h8);
        System.out.println(h6);
        System.out.println(h5);
        System.out.println(h4);
        System.out.println(h3);
        System.out.println(h2);
        System.out.println(h1);
    }

    public static class Human
    {
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children;

        public Human(String name, boolean sex, int age, ArrayList<Human> children) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
