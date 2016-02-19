package com.javarush.test.level26.lesson10.task01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* Весь мир играет комедию.
Почитать про java.util.concurrent.locks.Lock на http://docs.oracle.com/  (там все есть в джавадоках!)
Написать реализацию метода someMethod:
1. попытаться захватить лок
1.1. если лок занят, то вызвать метод ifLockIsBusy
1.2. если лок свободен, то:
1.2.1 вызвать метод ifLockIsFree
1.2.2 отпустить лок при любых условиях, даже если ifLockIsFree будет кидать исключение
*/
public class Solution {
    protected Lock lock = new ReentrantLock();

    public void someMethod() {
        //implement logic here, use the lock field
        if (lock.tryLock()) {
            try {
                ifLockIsFree();
            } finally {
                lock.unlock();
            }
        } else {
            ifLockIsBusy();
        }
    }

    public void ifLockIsFree() {
    }

    public void ifLockIsBusy() {
    }
}
