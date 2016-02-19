package com.javarush.test.level34.lesson15.big01.controller;

import com.javarush.test.level34.lesson15.big01.model.Direction;
import com.javarush.test.level34.lesson15.big01.model.GameObjects;
import com.javarush.test.level34.lesson15.big01.model.Model;
import com.javarush.test.level34.lesson15.big01.view.View;

import java.io.File;
import java.nio.file.Paths;

/**
 * Created by Олег Волков on 14.02.2016.
 */
public class Controller implements EventListener
{
    private View view;
    private Model model;

    public Controller()
    {
        this.view = new View(this);
        this.model = new Model();
        view.init();
        model.restart();
        model.setEventListener(this);
        view.setEventListener(this);

    }

    public static void main(String ... args) {
        Controller controller =  new Controller();
    }

    @Override
    public void move(Direction direction)
    {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart()
    {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel()
    {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level)
    {
        view.completed(level);
    }


    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }
}
