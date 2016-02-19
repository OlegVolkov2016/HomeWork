package com.javarush.test.level11.lesson11.home02;

/* AppleIPhone и SamsungGalaxyS2
Написать два класса AppleIPhone и SamsungGalaxyS2.
Унаследовать SamsungGalaxyS2 от AppleIPhone.
*/

public class Solution
{
    public static void main(String[] args)
    {
    }

    public class AppleIPhone
    {
        private String name;
        public AppleIPhone(String name) {
            this.name = name;
        }
    }

    public class SamsungGalaxyS2 extends AppleIPhone
    {
        private int mark;
        public SamsungGalaxyS2(String name, int mark) {
            super(name);
            this.mark = mark;
        }
    }
}
