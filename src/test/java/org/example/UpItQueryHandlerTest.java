package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UpItQueryHandlerTest {
    @Test
    void set() {
        UpItQueryHandler queryHandler = new UpItQueryHandler();
        queryHandler.initStartingSequence("1 2 3 4 5");

        queryHandler.set(5, 5, 0);
        Assertions.assertEquals("[1, 2, 3, 4, 0]", queryHandler.getTreapInString());

        queryHandler.set(1, 1, 0);
        Assertions.assertEquals("[0, 2, 3, 4, 0]", queryHandler.getTreapInString());

        queryHandler.set(2, 4, 0);
        Assertions.assertEquals("[0, 0, 0, 0, 0]", queryHandler.getTreapInString());

        queryHandler.set(1, 5, 5);
        Assertions.assertEquals("[5, 5, 5, 5, 5]", queryHandler.getTreapInString());
    }

    @Test
    void add() {
        UpItQueryHandler queryHandler = new UpItQueryHandler();
        queryHandler.initStartingSequence("0 0 0 0 0");

        queryHandler.add(1, 5, 1);
        Assertions.assertEquals("[1, 2, 3, 4, 5]", queryHandler.getTreapInString());

        queryHandler.add(2, 5, 10);
        Assertions.assertEquals("[1, 12, 23, 34, 45]", queryHandler.getTreapInString());

        queryHandler.add(3, 4, 100);
        Assertions.assertEquals("[1, 12, 123, 234, 45]", queryHandler.getTreapInString());
    }

    @Test
    void insert() {
        UpItQueryHandler queryHandler = new UpItQueryHandler();
        queryHandler.initStartingSequence("0 0 0 0 0");

        queryHandler.insert(5, 5);
        Assertions.assertEquals("[0, 0, 0, 0, 5, 0]", queryHandler.getTreapInString());

        queryHandler.insert(4, 5);
        Assertions.assertEquals("[0, 0, 0, 5, 0, 5, 0]", queryHandler.getTreapInString());

        queryHandler.insert(3, 5);
        Assertions.assertEquals("[0, 0, 5, 0, 5, 0, 5, 0]", queryHandler.getTreapInString());

        queryHandler.insert(2, 5);
        Assertions.assertEquals("[0, 5, 0, 5, 0, 5, 0, 5, 0]", queryHandler.getTreapInString());

        queryHandler.insert(1, 5);
        Assertions.assertEquals("[5, 0, 5, 0, 5, 0, 5, 0, 5, 0]", queryHandler.getTreapInString());

        queryHandler.insert(5, 5);
        Assertions.assertEquals("[5, 0, 5, 0, 5, 5, 0, 5, 0, 5, 0]", queryHandler.getTreapInString());
    }

    @Test
    void find() {
        UpItQueryHandler queryHandler = new UpItQueryHandler();
        queryHandler.initStartingSequence("1 2 3 4 5");

        Assertions.assertEquals(1, queryHandler.find(1, 1));
        Assertions.assertEquals(3, queryHandler.find(1, 2));
        Assertions.assertEquals(6, queryHandler.find(1, 3));
        Assertions.assertEquals(10, queryHandler.find(1, 4));
        Assertions.assertEquals(15, queryHandler.find(1, 5));
        Assertions.assertEquals(9, queryHandler.find(2, 4));
        Assertions.assertEquals(3, queryHandler.find(3, 3));
        Assertions.assertEquals(5, queryHandler.find(5, 5));
    }
}