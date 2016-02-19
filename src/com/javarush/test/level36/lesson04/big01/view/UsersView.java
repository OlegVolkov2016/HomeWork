package com.javarush.test.level36.lesson04.big01.view;

import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.controller.Controller;
import com.javarush.test.level36.lesson04.big01.model.ModelData;

import java.util.List;

/**
 * Created by Олег on 15.02.2016.
 */
public class UsersView implements View
{
    private Controller controller;

    @Override
    public void refresh(ModelData modelData)
    {
        String dataDivider = "===================================================";
        if (modelData.isDisplayDeletedUserList()) {
            System.out.println("All deleted users:");
        } else {
            System.out.println("All users:");
        }
        List<User> list = modelData.getUsers();
        for (User user : list) {
            System.out.println("\t" + user.toString());
        }
        System.out.println(dataDivider);
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void fireEventShowAllUsers() {
        controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }

    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }
}
