package com.javarush.test.level15.lesson04.task02;

/* ООП - Перегрузка
Перегрузите метод printMatrix 8 различными способами. В итоге должно получиться 10 различных методов printMatrix.
*/

public class Solution {
    public static void main(String[] args) {
        printMatrix(2, 3, "8");
    }

    public static void printMatrix(int m, int n, String value) {
        System.out.println("Заполняем объектами String");
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, int n, Object value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value);
            }
            System.out.println();
        }
    }

    public static void printMatrix(int m, int n, Integer value) {}
    public static void printMatrix(double m, int n, Integer value) {}
    public static void printMatrix(float m, int n, Integer value) {}
    public static void printMatrix(byte m, int n, Integer value) {}
    public static void printMatrix(short m, int n, Integer value) {}
    public static void printMatrix(int m, float n, Integer value) {}
    public static void printMatrix(int m, double n, Integer value) {}
    public static void printMatrix(int m, short n, Integer value) {}
}