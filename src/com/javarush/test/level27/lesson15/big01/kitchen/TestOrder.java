package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Олег Волков on 27.02.2016.
 */
public class TestOrder extends Order
{
    public TestOrder(Tablet tablet) throws IOException
    {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException
    {
        dishes = new ArrayList<>();
        for (int i = 0; i < Dish.values().length; i++)
        {
            int index = (int) (Math.random() * (Dish.values().length));
            dishes.add(Dish.values()[index]);
        }
    }
}
