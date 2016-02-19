package com.javarush.test.level36.lesson04.big01.view;

import com.javarush.test.level36.lesson04.big01.controller.Controller;
import com.javarush.test.level36.lesson04.big01.model.ModelData;

/**
 * Created by Олег on 15.02.2016.
 */
public interface View
{
    public void refresh(ModelData modelData);
    public void setController(Controller controller);
}
