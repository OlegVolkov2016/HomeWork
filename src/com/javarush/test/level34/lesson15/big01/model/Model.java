package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;

/**
 * Created by Олег Волков on 14.02.2016.
 */
public class Model
{
    public static final int FIELD_SELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("i:/idea/javarushhomework/src/com/javarush/test/level34/lesson15/big01/res/levels.txt"));

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(gameObjects.getPlayer(), direction) || checkBoxCollision(direction)) return;
        switch (direction){
            case LEFT:
                player.move(-FIELD_SELL_SIZE,0);
                break;
            case RIGHT:
                player.move(FIELD_SELL_SIZE,0);
                break;
            case UP:
                player.move(0,-FIELD_SELL_SIZE);
                break;
            case DOWN:
                player.move(0,FIELD_SELL_SIZE);
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()){
            if(gameObject.isCollision(wall, direction)){
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction) {
        Player player = gameObjects.getPlayer();
        GameObject stoped = null;
        for (GameObject gameObject: gameObjects.getAll()){
            if (!(gameObject instanceof Player) && !(gameObject instanceof Home) && player.isCollision(gameObject,direction)) {
                stoped = gameObject;
            }
        }
        if ((stoped == null)) {
            return false;
        }
        if (stoped instanceof Box) {
            Box stopedBox = (Box)stoped;
            if (checkWallCollision(stopedBox, direction)) {
                return true;
            }
            for (Box box : gameObjects.getBoxes()){
                if(stopedBox.isCollision(box, direction)) {
                    return true;
                }
            }
            switch (direction)
            {
                case LEFT:
                    stopedBox.move(-FIELD_SELL_SIZE, 0);
                    break;
                case RIGHT:
                    stopedBox.move(FIELD_SELL_SIZE, 0);
                    break;
                case UP:
                    stopedBox.move(0, -FIELD_SELL_SIZE);
                    break;
                case DOWN:
                    stopedBox.move(0, FIELD_SELL_SIZE);
                    break;
            }
        }
        return false;
    }

    public void checkCompletion() {
        boolean check = true;
        for (Home home : gameObjects.getHomes()) {
            boolean currentCheck = false;
            for (Box box: gameObjects.getBoxes()) {
                if ((box.getX() == home.getX()) && (box.getY() == home.getY()))
                    currentCheck = true;
            }
            if (!currentCheck) {
                check = false;
                break;
            }
        }
        if (check) {
            eventListener.levelCompleted(currentLevel);
        }
    }
}
