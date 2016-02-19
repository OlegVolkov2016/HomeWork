package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Олег Волков on 14.02.2016.
 */
public class Box extends CollisionObject implements Movable
{
    public Box(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.ORANGE);
        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;
        int rightUpperCornerX = getX() + getWidth() / 2;
        int rightUpperCornerY = leftUpperCornerY;
        int leftLowerCornerX = leftUpperCornerX;
        int leftLowerCornerY = getY() + getHeight() / 2;
        int rightLowerCornerX = rightUpperCornerX;
        int rightLowerCornerY = leftLowerCornerY;
        graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        graphics.fillRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
//        graphics.setColor(Color.BLACK);
//        graphics.drawLine(leftUpperCornerX, leftUpperCornerY, rightLowerCornerX, rightLowerCornerY);
//        graphics.drawLine(leftLowerCornerX, leftLowerCornerY, rightUpperCornerX, rightUpperCornerY);
    }

    @Override
    public void move(int x, int y)
    {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
}
