package com.javarush.test.level36.lesson04.home01;

import java.util.List;

/**
 * Created by Олег Волков on 20.02.2016.
 */
public class Model
{
    Service service = new Service();

    public List<String> getStringDataList() {
        return service.getData();
    }
}
