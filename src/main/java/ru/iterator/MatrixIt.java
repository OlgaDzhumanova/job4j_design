package ru.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length - 1 && data[row].length == 0) {
            row++;
        }
        return column < data[row].length;
    }

    @Override
    public Integer next() {
        while (row < data.length - 1 && column >= data[row].length) {
            row++;
            column = 0;
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}