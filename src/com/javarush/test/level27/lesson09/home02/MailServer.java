package com.javarush.test.level27.lesson09.home02;

public class MailServer implements Runnable {
    private Mail mail;

    public MailServer(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void run() {
        long beforeTime = System.currentTimeMillis();
        //сделайте что-то тут - do something here
        synchronized (mail) {
            try {
                mail.wait();
                String name = Thread.currentThread().getName();
                long afterTime = System.currentTimeMillis();
                System.out.format("%s MailServer has got: [%s] in %d ms after start", name, mail.getText(), (afterTime - beforeTime));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
