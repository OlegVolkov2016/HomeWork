package com.javarush.test.level05.lesson12.home02;

/* Man and Woman
1. Внутри класса Solution_1 создай public static классы Man и Woman.
2. У классов должны быть поля: name(String), age(int), address(String).
3. Создай конструкторы, в которые передаются все возможные параметры.
4. Создай по два объекта каждого класса со всеми данными используя конструктор.
5. Объекты выведи на экран в таком формате [name + " " + age + " " + address].
*/

public class Solution
{
    public static void main(String[] args)
    {
        Man man1 = new Man("a",10,"b");
        Man man2 = new Man(man1);
        Woman woman1 = new Woman("c",20,"d");
        Woman woman2 = new Woman(woman1);

        System.out.println(man1);
        System.out.println(man2);
        System.out.println(woman1);
        System.out.println(woman2);
    }

    public static class Man {
        String name;
        int age;
        String address;
        Man(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
        Man(Man man) {
            this.name = man.name;
            this.age = man.age;
            this.address = man.address;
        }
        public String toString() {
            return name + " " + age + " " + address;
        }
    }

    public static class Woman {
        String name;
        int age;
        String address;
        Woman(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
        Woman(Woman woman) {
            this.name = woman.name;
            this.age = woman.age;
            this.address = woman.address;
        }
        public String toString() {
            return name + " " + age + " " + address;
        }
    }
}
