package com.javarush.test.level33.lesson10.bonus01;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олег Волков on 27.02.2016.
 */
@XmlType(name = "first")
@XmlRootElement
public class First
{
    public List<String> second;
    public First()
    {
        this.second = new ArrayList<>();
    }
}
