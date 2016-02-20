package com.javarush.test.level36.lesson04.home01;

/**
 * Created by Олег Волков on 20.02.2016.
 */
public class View
{
    Controller controller = new Controller();

    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }
}
