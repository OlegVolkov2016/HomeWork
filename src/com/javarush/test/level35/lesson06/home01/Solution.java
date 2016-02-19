package com.javarush.test.level35.lesson06.home01;

/* Несколько суперклассов для дженерика
Дан класс Solution, параметризированный T.
Ограничьте параметр T.
T должен быть наследником класса ClassForGenerics и одновременно реализовывать интерфейс InterfaceForGenerics.
Менять можно только класс Solution.
*/
public class Solution<T extends ClassForGenerics & InterfaceForGenerics> {
    public static void main(String[] args) {
        Solution<TestClassGood> testClassSolution = new Solution<>();
        testClassSolution.check();

//        //!!! Следующие оба варианта не должны работать:
//        Solution<TestClassWrong1> wrong1Solution = new Solution<>();
//        wrong1Solution.check();
//
//        Solution<TestClassWrong2> wrong2Solution = new Solution<>();
//        wrong2Solution.check();
    }

    public void check() {
        System.out.println("Works!");
    }

    public static class TestClassGood extends ClassForGenerics implements InterfaceForGenerics {

    }

    public static class TestClassWrong1 extends ClassForGenerics {

    }

    public static class TestClassWrong2 implements InterfaceForGenerics {

    }

}
