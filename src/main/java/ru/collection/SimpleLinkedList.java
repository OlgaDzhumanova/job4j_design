package ru.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            size++;
            modCount++;
            return;
        }
        Node<E> last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = node;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = head;
        for (var i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E>  lastElement = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return lastElement != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = lastElement.item;
                lastElement = lastElement.next;
                return result;
            }

        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}