package com.javarush.test.level18.lesson08.task03;

import java.io.*;
import java.nio.channels.FileChannel;

/* AmigoOutputStream
1 Измените класс AmigoOutputStream так, чтобы он стал Wrapper-ом для класса FileOutputStream. Используйте наследование.
2 При вызове метода close() должны выполняться следующая последовательность действий:
2.1 вызвать метод flush()
2.2 дописать следующий текст [JavaRush © 2012-2013 All rights reserved.], используйте метод getBytes()
2.3 закрыть поток методом close()
*/

public class AmigoOutputStream extends FileOutputStream {
    public static String fileName = "C:/tmp/result.txt";
    private FileOutputStream component;

    public AmigoOutputStream(String name, FileOutputStream component) throws FileNotFoundException
    {
        super(name);
        this.component = component;
    }

    public AmigoOutputStream(String name, boolean append, FileOutputStream component) throws FileNotFoundException
    {
        super(name, append);
        this.component = component;
    }

    public AmigoOutputStream(File file, FileOutputStream component) throws FileNotFoundException
    {
        super(file);
        this.component = component;
    }

    public AmigoOutputStream(File file, boolean append, FileOutputStream component) throws FileNotFoundException
    {
        super(file, append);
        this.component = component;
    }

    public AmigoOutputStream(FileDescriptor fdObj, FileOutputStream component)
    {
        super(fdObj);
        this.component = component;
    }

    public AmigoOutputStream(FileOutputStream component) throws FileNotFoundException
    {
        super(fileName);
        this.component = component;
    }

    @Override
    public void close() throws IOException
    {
        component.flush();
        String s = "JavaRush © 2012-2013 All rights reserved.";
        component.write(s.getBytes());
        component.close();
    }

    @Override
    public void write(int b) throws IOException
    {
        component.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException
    {
        component.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException
    {
        component.write(b, off, len);
    }

    @Override
    protected void finalize() throws IOException
    {
        super.finalize();
    }

    @Override
    public void flush() throws IOException
    {
        component.flush();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

}
