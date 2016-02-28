package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Олег Волков on 28.02.2016.
 */
public class Home extends GameObject
{
    public Home(int x, int y)
    {
        super(x, y);
        setHeight(2);
        setWidth(2);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.RED);
        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;
//        int leftUpperCornerX = getX() + Model.FIELD_SELL_SIZE / 2 - getWidth() / 2;
//        int leftUpperCornerY = getY() + Model.FIELD_SELL_SIZE / 2 - getHeight() / 2;
        graphics.drawOval(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
    }
}

