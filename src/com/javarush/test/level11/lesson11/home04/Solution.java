package com.javarush.test.level11.lesson11.home04;

/* Религии
Написать три класса: Judaism(Иудаизм), Christianity(Христианство), Islam(Мусульманство)
Унаследовать христианство от иудаизма и мусульманство от христианства.
*/

public class Solution
{
    public static void main(String[] args)
    {
    }

    public class Judaism
    {
        private String name;
        public Judaism(String name) {
            this.name = name;
        }
    }

    public class Christianity extends Judaism
    {
        private String church;
        public Christianity(String name, String church) {
            super(name);
            this.church = church;
        }
    }

    public class Islam extends Christianity
    {
        private String mosque;
        public Islam(String name, String church, String mosque) {
            super(name, church);
            this.mosque = mosque;
        }
    }

}
