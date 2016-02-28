package com.javarush.test.level27.lesson15.big01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олег Волков on 27.02.2016.
 */
public class RandomOrderGeneratorTask implements Runnable
{
    private List<Tablet> tablets = new ArrayList<>();
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval)
    {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run()
    {
        if (tablets.isEmpty())
            return;
        while(!Thread.currentThread().isInterrupted() ){
            Tablet tablet = tablets.get((int)(Math.random() * (tablets.size())));
            tablet.createTestOrder();
            try
            {
                Thread.sleep(interval);
            }
            catch (InterruptedException e){
                return;
            }
        }

    }
}
