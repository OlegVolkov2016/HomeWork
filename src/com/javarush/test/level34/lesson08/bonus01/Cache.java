package com.javarush.test.level34.lesson08.bonus01;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<K, V>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        V value = cache.get(key);
        if (value == null) {
            value = clazz.getConstructor(key.getClass()).newInstance(key);
            cache.put(key, value);
//            put(value);
        }
        return value;
    }

    public boolean put(V obj) {
        //TODO add your code here
        try
        {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            K key = (K) method.invoke(obj);
            cache.put(key, obj);

        }
        catch (NoSuchMethodException e)
        {
            return false;
        }
        catch (InvocationTargetException e)
        {
            return false;
        }
        catch (IllegalAccessException e)
        {
            return false;
        }
        return true;
    }

    public int size() {
        return cache.size();
    }
}
