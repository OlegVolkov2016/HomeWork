package com.javarush.test.level32.lesson15.big01.listeners;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

/**
 * Created by Олег Волков on 13.02.2016.
 */
public class UndoListener implements UndoableEditListener
{
    private UndoManager undoManager;

    public UndoListener(UndoManager undoManager)
    {
        this.undoManager = undoManager;
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent e)
    {
        undoManager.addEdit(e.getEdit());
    }
}
