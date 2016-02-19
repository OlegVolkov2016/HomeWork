package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by Олег Волков on 05.02.2016.
 */
public class Hippodrome
{
    private ArrayList<Horse> horses = new ArrayList<>();

    public static Hippodrome game;

    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        System.out.println();
        System.out.println();
    }

    public Horse getWinner() {
        double max = 0;
        Horse winner = null;
        for (Horse horse : horses) {
            if (horse.getDistance() > max) {
                max = horse.getDistance();
                winner = horse;
            }
        }
        return winner;
    }

    public void printWinner() {
        Horse winner = getWinner();
        System.out.println("Winner is " + winner.getName() + "!");
    }

    public static void main(String[] args) {
        game = new Hippodrome();
        Horse horse1 = new Horse("Horse 1", 3, 0);
        Horse horse2 = new Horse("Horse 2", 3, 0);
        Horse horse3 = new Horse("Horse 3", 3, 0);

        game.getHorses().add(horse1);
        game.getHorses().add(horse2);
        game.getHorses().add(horse3);

        game.run();
        game.printWinner();
    }
}
