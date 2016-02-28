package com.javarush.test.level34.lesson15.big01.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Олег Волков on 28.02.2016.
 */
public class LevelLoader {
    private Path levels;
    public LevelLoader(Path levels) {
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
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile())))
        {
            while (!reader.readLine().contains("Maze: " + levl)) ;
            reader.readLine();
            int x = Integer.parseInt(reader.readLine().split(" ")[2]);
            int y = Integer.parseInt(reader.readLine().split(" ")[2]);
            reader.readLine();
            reader.readLine();
            reader.readLine();
            for (int i = 0; i < y; i++)
            {
                String line = reader.readLine();
                for (int j = 0; j < x; j++)
                {
                    switch (line.charAt(j))
                    {
                        case 'X':
                            walls.add(new Wall((Model.FIELD_SELL_SIZE / 2) + (j * Model.FIELD_SELL_SIZE), (Model.FIELD_SELL_SIZE / 2) + (i * Model.FIELD_SELL_SIZE)));
                            break;
                        case '*':
                            boxes.add(new Box((Model.FIELD_SELL_SIZE / 2) + (j * Model.FIELD_SELL_SIZE), (Model.FIELD_SELL_SIZE / 2) + (i * Model.FIELD_SELL_SIZE)));
                            break;
                        case '.':
                            homes.add(new Home((Model.FIELD_SELL_SIZE / 2) + (j * Model.FIELD_SELL_SIZE), (Model.FIELD_SELL_SIZE / 2) + (i * Model.FIELD_SELL_SIZE)));
                            break;
                        case '&':
                            boxes.add(new Box((Model.FIELD_SELL_SIZE / 2) + (j * Model.FIELD_SELL_SIZE), (Model.FIELD_SELL_SIZE / 2) + (i * Model.FIELD_SELL_SIZE)));
                            homes.add(new Home((Model.FIELD_SELL_SIZE / 2) + (j * Model.FIELD_SELL_SIZE), (Model.FIELD_SELL_SIZE / 2) + (i * Model.FIELD_SELL_SIZE)));
                            break;
                        case '@':
                            player = new Player((Model.FIELD_SELL_SIZE / 2) + (j * Model.FIELD_SELL_SIZE), (Model.FIELD_SELL_SIZE / 2) + (i * Model.FIELD_SELL_SIZE));
                            break;
                    }
                }
            }

        } catch (IOException e) {

        }
        return new GameObjects(walls, boxes, homes, player);
    }
}

