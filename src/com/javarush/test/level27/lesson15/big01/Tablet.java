package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Олег Волков on 07.02.2016.
 */
public class Tablet
{
    final static Logger logger = Logger.getLogger(Tablet.class.getName());
    private final int number;
    private LinkedBlockingQueue<Order> queue;

    public Tablet(int number) {
        this.number = number;
    }

    public void createOrder()
    {
        Order order = null;
        try
        {
            order = new Order(this);
            processOrder(order);
        }
        catch (NoVideoAvailableException e)
        {
            logger.log(Level.INFO,"No video is available for the order " + order);
        }
        catch (IOException e)
        {
            logger.log(Level.SEVERE,"Console is unavailable.");
        }
        catch (InterruptedException e)
        {

        }
    }

    private void processOrder(Order order) throws NoVideoAvailableException, IOException, InterruptedException
    {
        ConsoleHelper.writeMessage(order.toString());
        if(!order.isEmpty()) {
            queue.put(order);
            AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
            advertisementManager.processVideos();
        }
    }

    public void createTestOrder() {
        Order order = null;
        try
        {
            order = new TestOrder(this);
            processOrder(order);
        }
        catch (NoVideoAvailableException e)
        {
            logger.log(Level.INFO,"No video is available for the order " + order);
        }
        catch (IOException e)
        {
            logger.log(Level.SEVERE,"Console is unavailable.");
        }
        catch (InterruptedException e)
        {

        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Tablet{number=" + number + "}";
    }

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }
}
