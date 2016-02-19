package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;
import com.javarush.test.level33.lesson15.big01.Helper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Олег Волков on 14.02.2016.
 */
public class FileBucket
{
    private Path path;

    public FileBucket()
    {
        try
        {
            path = Files.createTempFile(null, null);
            path.toFile().deleteOnExit();
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public long getFileSize()
    {
        try
        {
            return Files.size(path);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
            return 0;
        }
    }

    public void putEntry(Entry entry)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path)))
        {
            oos.writeObject(entry);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry()
    {
        if (getFileSize() == 0)
        {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path)))
        {
            return (Entry) ois.readObject();
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
        catch (ClassNotFoundException e)
        {
            ExceptionHandler.log(e);
        }
        return null;
    }

    public void remove()
    {
        try
        {
            Files.delete(path);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
    }
}