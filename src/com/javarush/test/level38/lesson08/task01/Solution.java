package com.javarush.test.level38.lesson08.task01;

/* Предопределенные типы аннотаций (Predefined Annotation Types)
Расставьте в этом классе, везде где только можно, все возможные предопределенные в Java аннотации.
Не должно быть избыточности.
*/

public class Solution {

    private String[] arguments;

    public Solution(String... arguments) {
        this.arguments = arguments;
    }

    public void voidMethod() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        new Solution().new SubSolution().voidMethod();
    }

    class SubSolution extends Solution {

        public void voidMethod() throws Exception {
            super.voidMethod();
        }
    }
}
