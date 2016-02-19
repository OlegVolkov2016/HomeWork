package com.javarush.test.level37.lesson04.big01;

import com.javarush.test.level37.lesson04.big01.male.MaleFactory;

/**
 * Created by Олег Волков on 19.02.2016.
 */
public class Solution
{
    public static void main(String[] args)
    {
        MaleFactory maleFactory = new MaleFactory();
        Human human1 = maleFactory.getPerson(99);
        Human human2 = maleFactory.getPerson(4);
        Human human3 = maleFactory.getPerson(15);
        System.out.println(human1);
        System.out.println(human2);
        System.out.println(human3);
    }
}
