package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Олег Волков on 09.02.2016.
 */
public class MyThread extends Thread
{
    private static AtomicInteger currentPriority = new AtomicInteger(0);

    public MyThread()
    {
        initPriority();
    }

    public MyThread(Runnable target)
    {
        super(target);
        initPriority();
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        initPriority();
    }

    public MyThread(String name)
    {
        super(name);
        initPriority();
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        initPriority();
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        initPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        initPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        initPriority();
    }

    private void initPriority() {
        currentPriority.incrementAndGet();
        currentPriority.compareAndSet(11, 1);

        int newPriority = currentPriority.get();
        if (getThreadGroup() != null) {
            if (newPriority > getThreadGroup().getMaxPriority()) {
                newPriority = getThreadGroup().getMaxPriority();
            }
        }
        setPriority(newPriority);
    }
}
