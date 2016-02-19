package com.javarush.test.level27.lesson09.home01;

public class ConsumerTask implements Runnable {
    private TransferObject transferObject;
    protected volatile boolean stopped;

    public ConsumerTask(TransferObject transferObject) {
        this.transferObject = transferObject;
        new Thread(this, "ConsumerTask").start();
    }

    public void run() {
        while (!stopped) {
            transferObject.get();
        }
    }

    public void stop() {
        stopped = true;
    }
}