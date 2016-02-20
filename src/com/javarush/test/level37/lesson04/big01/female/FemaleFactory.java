package com.javarush.test.level37.lesson04.big01.female;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by Олег Волков on 19.02.2016.
 */
public class FemaleFactory implements AbstractFactory
{

    @Override
    public Human getPerson(int age)
    {
        if (age <= KidGirl.MAX_AGE) {
            return new KidGirl();
        } else if (KidGirl.MAX_AGE < age && age <= TeenGirl.MAX_AGE) {
            return new TeenGirl();
        } else {
            return new Woman();
        }
    }
}
