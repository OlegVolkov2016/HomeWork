package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Олег Волков on 07.02.2016.
 */
public class Cook extends Observable implements Runnable
{
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order)
    {
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        StatisticEventManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(),name,order.getTotalCookingTime()*60,order.getDishes()));
        try
        {
            Thread.sleep(order.getTotalCookingTime() * 10);
        }
        catch (InterruptedException e)
        {

        }
        setChanged();
        notifyObservers(order);
        busy = false;
    }

    public boolean isBusy()
    {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                if (!queue.isEmpty())
                {
                    Order order = queue.poll();
                    if (order != null)
                    {
                        this.startCookingOrder(order);
                    }
                }
                Thread.sleep(10);
            }
        }
        catch (InterruptedException e)
        {
        }
    }
}
