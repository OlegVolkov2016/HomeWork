package com.javarush.test.level37.lesson04.task01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/* Круговой итератор
Класс Solution наследуется от ArrayList.
Напишите свой класс RoundIterator внутри Solution, который будет итератором для списка Solution.
Итератор должен ходить по кругу по всем элементам.
В остальном поведение должно быть идентичным текущему итератору.
*/
public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new RoundIterator(super.listIterator());
    }

    public class RoundIterator implements ListIterator<T>
    {
        private ListIterator<T> iterator;

        public RoundIterator(ListIterator<T> iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {
            return (Solution.this.size() > 0);
        }
        @Override
        public T next() {
            if (!iterator.hasNext()) iterator = Solution.this.listIterator();
            return iterator.next();
        }
        @Override
        public void remove() {
            iterator.remove();
        }
//        @Override
//        public void forEachRemaining(Consumer<? super T> action) {
//            iterator.forEachRemaining(action);
//        }
        @Override
        public boolean hasPrevious() {
            return (Solution.this.size() > 0);
        }
        @Override
        public T previous() {
            return iterator.previous();
        }
        @Override
        public int nextIndex() {
            return iterator.nextIndex();
        }
        @Override
        public int previousIndex() {
            return iterator.previousIndex();
        }
        @Override
        public void set(T t) {
            iterator.set(t);
        }
        @Override
        public void add(T t) {
            iterator.add(t);
        }
    }
}
