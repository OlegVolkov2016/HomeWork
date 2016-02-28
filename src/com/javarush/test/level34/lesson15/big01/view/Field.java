package com.javarush.test.level34.lesson15.big01.view;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.Direction;
import com.javarush.test.level34.lesson15.big01.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Олег Волков on 28.02.2016.
 */
public class Field extends JPanel
{
    private EventListener eventListener;
    private View view;
    public Field(View view) {
        this.view = view;
        Field.KeyHandler keyHandler = new KeyHandler(this);
        addKeyListener(keyHandler);
        setFocusable(true);
    }
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        for (GameObject object : view.getGameObjects().getAll())
            object.draw(g);
    }
    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
    public static class KeyHandler extends KeyAdapter
    {
        Field field;
        public KeyHandler(Field field) {
            this.field = field;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    field.eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    field.eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    field.eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    field.eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R:
                    field.eventListener.restart();
                    break;
            }
        }
    }
}