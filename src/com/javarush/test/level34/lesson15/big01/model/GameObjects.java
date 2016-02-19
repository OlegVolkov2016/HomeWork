package com.javarush.test.level34.lesson15.big01.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Олег Волков on 14.02.2016.
 */
public class GameObjects
{
    private Set<Wall> walls;
    private Set<Box> boxes;
    private Set<Home> homes;
    private Player player;

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player)
    {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    public Set<GameObject> getAll()
    {
        Set gameObjects = new HashSet();
        gameObjects.addAll(walls);
        gameObjects.addAll(boxes);
        gameObjects.addAll(homes);
        gameObjects.add(player);
        return gameObjects;
    }

    public Set<Wall> getWalls()
    {
        return walls;
    }

    public Set<Box> getBoxes()
    {
        return boxes;
    }

    public Set<Home> getHomes()
    {
        return homes;
    }

    public Player getPlayer()
    {
        return player;
    }
}
