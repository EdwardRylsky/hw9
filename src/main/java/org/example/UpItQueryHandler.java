package org.example;

import java.util.Arrays;

public class UpItQueryHandler {
    private final static int MIN_PARAMETER_VALUE = 0;
    private final static int MAX_PARAMETER_VALUE = 100_000;

    // Согласно условиям задачи минимальное значение параметра X = 1.
    // Однако в примерах дают X = 0, поэтому задаем такое минимальное значение
    private final static int MIN_X_VALUE = 0;
    private final static int MAX_X_VALUE = 100;
    private final Treap treap = new Treap();

    public void initStartingSequence(String s) {
        Arrays.stream(s.split(" "))
                .map(Integer::parseInt)
                .peek(UpItQueryHandler::checkParameter)
                .forEach(treap::add);
    }

    /**
     * 1. Set all elements from A-th to B-th
     * (inclusive) to value X
     * @param a starting index
     * @param b ending index
     * @param x settable value
     */
    public void set(int a, int b, int x) {
        checkXParameter(x);

        for (int i = a; i <= b; i++) {
            insert(i, x);
            treap.remove(i);
        }
    }

    /**
     * 2. Add X to A-th element, 2*X to
     * (A+1)-th , …, and (B-A+1)*X to the
     * B-th element
     * @param a starting index
     * @param b ending index
     * @param x addable value
     */
    public void add(int a, int b, int x) {
        checkXParameter(x);
        for (int i = a; i <= b; i++) {
            treap.addToAll(i, b, x);
        }
    }

    /**
     * 3. Insert new element with value X
     * immediately before the C-th element
     * @param c insertion index
     * @param x inserting value
     */
    public void insert(int c, int x) {
        checkXParameter(x);
        treap.add(c-1, x);
    }

    /**
     * 4. Find the sum of all elements from
     * A-th to B-th
     * @param a starting index
     * @param b ending index
     */
    public long find(int a, int b) {
        Treap.Node[] splitA = treap.split(a - 1);
        Treap.Node[] splitB = splitA[1].split(b - a + 1);
        return splitB[0].statistic.sumValue;
    }

    public String getTreapInString() {
        return treap.inorder().toString();
    }

    private void checkXParameter(int x) {
        if (x < MIN_X_VALUE) {
            throw new IllegalArgumentException("X have to be greater or equal than " + MIN_X_VALUE);
        }
        if (x > MAX_X_VALUE) {
            throw new IllegalArgumentException("X have to be less or equal than " + MAX_X_VALUE);
        }
    }

    private static void checkParameter(int parameter) {
        if (parameter < MIN_PARAMETER_VALUE) {
            throw new IllegalArgumentException("Parameter have to be greater or equal than " + MIN_PARAMETER_VALUE);
        }
        if (parameter > MAX_PARAMETER_VALUE) {
            throw new IllegalArgumentException("Parameter have to be less or equal than " + MAX_PARAMETER_VALUE);
        }
    }
}