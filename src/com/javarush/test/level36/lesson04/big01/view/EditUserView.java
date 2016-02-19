package com.javarush.test.level36.lesson04.big01.view;

import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.controller.Controller;
import com.javarush.test.level36.lesson04.big01.model.ModelData;

import java.util.List;

/**
 * Created by Олег on 15.02.2016.
 */
public class EditUserView implements View
{
    private Controller controller;

    @Override
    public void refresh(ModelData modelData)
    {
        String dataDivider = "===================================================";
        System.out.println("User to be edited:");
        System.out.println("\t" + modelData.getActiveUser());
        System.out.println(dataDivider);
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void fireEventUserDeleted(long id) {
        controller.onUserDelete(id);
    }

    public void fireEventUserChanged(String name, long id, int level) {
        controller.onUserChange(name, id, level);
    }

}
