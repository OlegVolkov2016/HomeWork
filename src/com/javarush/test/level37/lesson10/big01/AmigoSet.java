package com.javarush.test.level37.lesson10.big01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Олег on 15.02.2016.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E>
{
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

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
        return map.keySet().iterator();
    }

    @Override
    public int size()
    {
        return map.keySet().size();
    }

    @Override
    public boolean add(E e)
    {
        return (null == map.put(e,PRESENT));
    }

    @Override
    public boolean isEmpty()
    {
        return map.keySet().isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return map.keySet().contains(o);
    }

    @Override
    public void clear()
    {
        map.keySet().clear();
    }

    @Override
    public boolean remove(Object o)
    {
        return map.keySet().remove(o);
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        AmigoSet<E> amigoSet = new AmigoSet<>();
        try
        {
            amigoSet.addAll(this);
            amigoSet.map.putAll(this.map);
        } catch (Exception e){
            throw new InternalError();
        }
        return amigoSet;
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException
    {
        outputStream.defaultWriteObject();
        outputStream.writeObject(map.size());
        for(E e : map.keySet()) {
            outputStream.writeObject(e);
        }
        outputStream.writeInt((int) HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        outputStream.writeFloat((float) HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
    }

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException
    {
        inputStream.defaultReadObject();
        int size = (int) inputStream.readObject();
        Set<E> set = new HashSet<>();
        for (int i =0; i<size; i++) {
            set.add((E) inputStream.readObject());
        }
        int capacity = inputStream.readInt();
        float loadFactor = inputStream.readFloat();
        map = new HashMap<>(capacity, loadFactor);
        for(E e : set){
            map.put(e, PRESENT);
        }
    }
}
