package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution_1 создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution_1.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution_1.
*/

public class Solution {
    private Solution(Boolean B){}
    private Solution(Double d){}
    private Solution(String s){}
    public Solution(byte b){}
    public Solution(short s){}
    public Solution(int i){}
    protected Solution(long l){}
    protected Solution(float f){}
    protected Solution(double d){}
    Solution(Object S){}
    Solution(Integer I){}
    Solution(Long L){}
}

