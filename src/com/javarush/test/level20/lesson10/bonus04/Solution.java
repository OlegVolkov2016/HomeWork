package com.javarush.test.level20.lesson10.bonus04;

import java.io.*;
import java.util.*;

public class Solution
        extends AbstractList<String>
        implements List<String>, Cloneable, Serializable
{
    public static void main(java.lang.String[] args) throws IOException, ClassNotFoundException {
        List<String> list = new Solution();
        for (int i = 1; i < 16; i++)
        {
            list.add(String.valueOf(i));
        }

        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));
        list.clear();
        System.out.println(list.size());
    }


    int size = 0;

    Node<String> first;

    Node<String> last;

    //new variable, it's needed for adding new elements
    Node<String> lastTA;

    @Override
    public void clear() {
        remove(first);
        remove(last);
        first = null;
        last = null;
        size = 0;
    }

    public java.lang.String getParent(java.lang.String value) {
        java.lang.String result = null;
        for (Node<String> temp = first; temp != null; temp = temp.next) {
            if (temp.item.equals(value)) {
                if (temp.parent == null)
                    return null;
                else
                    return java.lang.String.valueOf(temp.parent.item);
            }
        }
        return result;
    }

    void linkLast(String e) {
        final Node<String> l = last;
        final Node<String> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
            lastTA = newNode;
        }
        else if (l == first)
            l.next = newNode;
        else {
            l.next = newNode;
            if (lastTA.child2 != null)
                lastTA = lastTA.next;

            newNode.parent = lastTA;

            if (lastTA.child1 == null)
                lastTA.child1 = newNode;
            else
                lastTA.child2 = newNode;
        }

        size++;
        modCount++;
    }

    void linkBefore(String e, Node<String> succ) {
        // assert succ != null;
        final Node<String> pred = succ.prev;
        final Node<String> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }

    String unlink(Node<String> x) {
        // assert x != null;
        final String element = x.item;
        final Node<String> next = x.next;
        final Node<String> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        if (x.parent != null){
            if (x.parent.child1 == x)
                x.parent.child1 = null;
            else
                x.parent.child2 = null;
        }
        x.item = null;
        size--;
        modCount++;
        return element;
    }

    public boolean add(String e) {
        linkLast(e);
        return true;
    }



    public boolean remove(Object o) {
        if (o == null) {
            for (Node<String> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<String> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    if (x == lastTA)
                        lastTA = x.next;
                    if (x.child1 != null)
                        remove(x.child1.item);
                    if (x.child2 != null)
                        remove(x.child2.item);
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public Iterator<String> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<String> {
        private Node<String> lastReturned = null;
        private Node<String> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        Itr() {
            // assert isPositionIndex(index);
            next = first;
            nextIndex = 0;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public String next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public String previous() {
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void remove() {
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<String> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
        }

        public void set(String e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForComodification();
            lastReturned.item = e;
        }

        public void add(String e) {
            checkForComodification();
            lastReturned = null;
            if (next == null)
                linkLast(e);
            else
                linkBefore(e, next);
            nextIndex++;
            expectedModCount++;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private static class Node<E> implements Serializable {
        E item;
        Node<E> parent;
        Node<E> prev;
        Node<E> child1;
        Node<E> child2;
        Node<E> next;


        private Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }


    }


    @Override
    public String get(int index)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }


}