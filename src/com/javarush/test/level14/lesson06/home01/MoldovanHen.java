package com.javarush.test.level14.lesson06.home01;

/**
 * Created by Олег Волков on 24.01.2016.
 */

class MoldovanHen extends Hen {
    @Override
    int getCountOfEggsPerMonth()
    {
        return 5;
    }
    String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.MOLDOVA + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
