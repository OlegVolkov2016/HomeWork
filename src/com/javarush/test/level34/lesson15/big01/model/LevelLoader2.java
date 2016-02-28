package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.model.Box;
import com.javarush.test.level34.lesson15.big01.model.GameObjects;
import com.javarush.test.level34.lesson15.big01.model.Home;
import com.javarush.test.level34.lesson15.big01.model.Model;
import com.javarush.test.level34.lesson15.big01.model.Player;
import com.javarush.test.level34.lesson15.big01.model.Wall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Олег Волков on 14.02.2016.
 */
public class LevelLoader2
{
    private Path levels;
    public LevelLoader2(Path levels) {
        this.levels = levels;
    }
    public GameObjects getLevel(int level) {
        int levl = level % 60;
        if (levl == 0) {
            levl = 60;
        }
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(levels.toString()));
            int x0 = Model.FIELD_SELL_SIZE / 2;
            int y0 = Model.FIELD_SELL_SIZE / 2;
            while (!reader.readLine().contains("Maze: " + levl));
            reader.readLine();
            int x = Integer.parseInt(reader.readLine().split(" ")[2]);
            int y = Integer.parseInt(reader.readLine().split(" ")[2]);
            reader.readLine();
            reader.readLine();
            reader.readLine();
            for (int i = 0; i < y; i++) {
                String line = reader.readLine();
                for (int j = 0; j < x; j++)
                    switch (line.charAt(j)) {
                        case 'X':
                            walls.add(new Wall(x0 + j * Model.FIELD_SELL_SIZE, y0 + i * Model.FIELD_SELL_SIZE));
                            break;
                        case '@':
                            player = new Player(x0 + j * Model.FIELD_SELL_SIZE, y0 + i * Model.FIELD_SELL_SIZE);
                            break;
                        case '*':
                            boxes.add(new Box(x0 + j * Model.FIELD_SELL_SIZE, y0 + i * Model.FIELD_SELL_SIZE));
                            break;
                        case '&':
                            boxes.add(new Box(x0 + j * Model.FIELD_SELL_SIZE, y0 + i * Model.FIELD_SELL_SIZE));
                            break;
                        case '.':
                            homes.add(new Home(x0 + j * Model.FIELD_SELL_SIZE, y0 + i * Model.FIELD_SELL_SIZE));
                            break;
                    }
            }
        }
        catch (Exception e) {
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
