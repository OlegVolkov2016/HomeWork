package com.javarush.test.level14.lesson06.home01;

/**
 * Created by Олег Волков on 24.01.2016.
 */

class UkrainianHen extends Hen {
    @Override
    int getCountOfEggsPerMonth()
    {
        return 10;
    }
    String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.UKRAINE + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
