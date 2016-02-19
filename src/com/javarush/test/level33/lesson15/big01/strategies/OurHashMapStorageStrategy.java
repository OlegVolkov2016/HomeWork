package com.javarush.test.level33.lesson15.big01.strategies;

/**
 * Created by Олег Волков on 14.02.2016.
 */
public class OurHashMapStorageStrategy implements StorageStrategy
{
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    int size;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;

    int hash(Long k) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
//        k ^= (k >>> 20) ^ (k >>> 12);
//        return (int) (k ^ (k >>> 7) ^ (k >>> 4));
        return k.hashCode();
    }

    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    Entry getEntry(Long key) {
        if (size == 0) return null;
        int hash = (key == null) ? 0 : hash(key);
        if (table[indexFor(hash, table.length)] != null) {
            for (Entry e = table[indexFor(hash, table.length)]; e!=null; e = e.next) {
                Object k;
                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) return e;
            }
        }
        return null;
    }

    void resize(int newCapacity) {
        int MAXIMUM_CAPACITY = 1 << 30;
        Entry[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
            }
            else if (newCapacity < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCapacity = oldThr;
        else {               // zero initial threshold signifies using defaults
            newCapacity = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCapacity * loadFactor;
            newThr = (newCapacity < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                    (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        Entry[] newTab = new Entry[newCapacity];
        transfer(newTab);
        table = newTab;
    }

    void transfer(Entry[] newTable) {
        Entry[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry e = src[j];
            if (e != null) {
                src[j] = null;
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                    }
                while (e != null);
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex)
    {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        if (size++ >= threshold) {
            resize(2 * table.length);
        }
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }

    @Override
    public boolean containsKey(Long key)
    {
        return (getEntry(key) != null);
    }

    @Override
    public boolean containsValue(String value)
    {
        if (value == null) {
            return false;
        }
        Entry[] tab = table;
        for (int i = 0; i < tab.length ; i++) {
            for (Entry e = tab[i] ; e != null ; e = e.next) {
                if (value.equals(e.value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value)
    {
        addEntry(hash(key), key, value, indexFor(hash(key),table.length));
    }

    @Override
    public Long getKey(String value)
    {
        if (value == null) return null;
        Entry[] tab = table;
        for (int i = 0; i < tab.length; i++)
        {
            for (Entry e = tab[i]; e != null; e = e.next)
            {
                if (value.equals(e.value))
                {
                    return e.getKey();
                }
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key)
    {
        if (key == null) {
            return null;
        }
        int hash = hash((long) key.hashCode());
        for (Entry e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                return e.value;
            }
        }
        return null;
//        return (null == getEntry(key)) ? null : getEntry(key).getValue();
    }
}
