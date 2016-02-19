package com.javarush.test.level37.lesson10.big01;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Олег on 15.02.2016.
 */
public class AmigoSet<E> extends AbstractSet<E>
{
    private static final Object PRESENT = new Object();
    private HashMap<E,Object> map;

    public AmigoSet()
    {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection)
    {
        map = new HashMap<>((int)Math.max(16,collection.size()/.75f));
        this.addAll(collection);
    }

    @Override
    public Iterator<E> iterator()
    {
        return null;
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public boolean add(E e)
    {
        return (null == map.put(e,PRESENT));
    }
}
