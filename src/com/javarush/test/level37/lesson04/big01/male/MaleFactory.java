package com.javarush.test.level37.lesson04.big01.male;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;
import com.javarush.test.level37.lesson04.big01.male.KidBoy;
import com.javarush.test.level37.lesson04.big01.male.Man;
import com.javarush.test.level37.lesson04.big01.male.TeenBoy;

/**
 * Created by Олег Волков on 19.02.2016.
 */
public class MaleFactory implements AbstractFactory
{

    @Override
    public Human getPerson(int age)
    {
        if (age <= KidBoy.MAX_AGE) {
            return new KidBoy();
        } else if (KidBoy.MAX_AGE < age && age <= TeenBoy.MAX_AGE) {
            return new TeenBoy();
        } else {
            return new Man();
        }
    }
}
