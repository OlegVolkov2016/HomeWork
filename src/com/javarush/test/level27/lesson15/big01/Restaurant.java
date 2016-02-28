package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Олег Волков on 07.02.2016.
 */
public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Cook amigo = new Cook("Amigo");
        Cook diego = new Cook("Diego");
        amigo.setQueue(QUEUE);
        diego.setQueue(QUEUE);

        Waitor waitor = new Waitor();
        amigo.addObserver(waitor);
        diego.addObserver(waitor);

        Thread amigoThread=new Thread(amigo);
        amigoThread.start();
        Thread diegoThread=new Thread(diego);
        diegoThread.start();

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(QUEUE);
            tablets.add(tablet);
        }

        Thread task = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        try
        {
            task.start();
            Thread.sleep(1000);
            task.interrupt();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        amigoThread.interrupt();
        diegoThread.interrupt();

        DirectorTablet directorTablet=new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
