package com.javarush.test.level14.lesson06.home01;

/**
 * Created by Олег Волков on 24.01.2016.
 */
abstract class Hen {
    abstract int getCountOfEggsPerMonth();
    String getDescription() {
        return "Я курица.";
    }
}
