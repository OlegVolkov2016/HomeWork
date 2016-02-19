package com.javarush.test.level14.lesson06.home01;

/**
 * Created by Олег Волков on 24.01.2016.
 */

class BelarusianHen extends Hen {
    @Override
    int getCountOfEggsPerMonth()
    {
        return 2;
    }
    String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.BELARUS + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
