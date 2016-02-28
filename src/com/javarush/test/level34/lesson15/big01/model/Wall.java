package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Олег Волков on 28.02.2016.
 */
public class Wall extends CollisionObject
{
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(new Color( 165, 42, 42 ));
        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;
//        int leftUpperCornerX = getX();
//        int leftUpperCornerY = getY();
        graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        graphics.fillRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());

    }
}
