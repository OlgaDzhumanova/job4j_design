package ru.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        grow();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T value = null;
        if (indexOf(index) == index) {
            value = container[index];
            container[index] = newValue;
        }
        return value;
    }

    @Override
    public T remove(int index) {
        T value = null;
        if (indexOf(index) == index) {
            value = container[index];
            System.arraycopy(container, index + 1, container, index, size - index - 1);
            container[size - 1] = null;
            size--;
            modCount++;
        }
        return value;
    }

    @Override
    public T get(int index) {
        return indexOf(index) == index ? container[index] : null;
    }

    @Override
    public int size() {
        return size;
    }

    private int indexOf(int index) {
        return Objects.checkIndex(index, size);
    }

    private void arrayCopy(int size) {
        if (container.length == size) {
           container = Arrays.copyOf(container, container.length * 2);
        }
    }

    private void grow() {
        if (container.length == size) {
            arrayCopy(size);
        }
        if (container.length == 0) {
            container = Arrays.copyOf(container, 10);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

           private int count = 0;
           private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }

        };
    }
}