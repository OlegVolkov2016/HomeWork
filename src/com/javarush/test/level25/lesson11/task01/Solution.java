package com.javarush.test.level25.lesson11.task01;

import java.util.Random;

/* Обеспечение отсутствия прерывания важной операции
Просмотрите метод moveMoney
Если RANDOM.nextInt(5000) больше порогового значения THRESHOLD_VALUE,
то обеспечьте переуступку кванта времени (переход хода для текущей нити)
Добавьте этот код в единственное допустимое место.
*/
public class Solution {
    private static final double THRESHOLD_VALUE = 500;
    private static final Random RANDOM = new Random();

    public synchronized void moveMoney(Account from, Account to, int amount) {
        from.setBalance(from.getBalance() - amount);
        if (RANDOM.nextInt(5000) > THRESHOLD_VALUE) {
            Thread.yield();
        }
        to.setBalance(to.getBalance() + amount);
    }

    private class Account {
        private int balance;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }
}
