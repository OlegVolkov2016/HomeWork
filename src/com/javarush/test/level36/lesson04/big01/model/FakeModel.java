package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олег on 15.02.2016.
 */
public class FakeModel implements Model
{
    private ModelData modelData = new ModelData();
    private List<User> list = new ArrayList<>();

    @Override
    public ModelData getModelData()
    {
        return modelData;
    }

    @Override
    public void loadUsers()
    {
        list.add(new User("A", 1, 1));
        list.add(new User("B", 2, 1));
        modelData.setUsers(list);
    }

    @Override
    public void loadDeletedUsers()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadUserById(long userId)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUserById(long id)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changeUserData(String name, long id, int level)
    {
        throw new UnsupportedOperationException();
    }
}
