package com.delvin;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Test;

public class AppTest {
    @Test
    public void testSize_1() {
        MyList<String> list = new MyList<>();
        list.pushBack("data_1");
        list.pushFront("data_2");
        list.pushBack("data_3");
        list.pushFront("data_4");
        list.pushBack("data_5");
        assertEquals(5, list.getSize());
    }

    @Test
    public void testSize_2() {
        MyList<String> list = new MyList<>();
        list.pushBack("data_1");
        list.pushBack("data_2");
        list.popBack();
        list.popFront();
        assertEquals(0, list.getSize());
    }

    @Test
    public void testSize_3() {
        MyList<String> list = new MyList<>();
        list.pushBack("data_1");
        list.pushFront("data_2");
        list.pushBack("data_3");
        list.pushFront("data_4");
        list.pushBack("data_5");
        list.clear();
        assertEquals(0, list.getSize());
    }

    @Test
    public void testPop_1() {
        MyList<String> list = new MyList<>();
        list.pushBack("data_1");
        assertEquals("data_1", list.popBack());
    }

    @Test
    public void testPop_2() {
        MyList<String> list = new MyList<>();
        list.pushBack("data_1");
        list.pushFront("data_2");
        assertEquals("data_1", list.popBack());
    }

    @Test
    public void testPop_3() {
        MyList<String> list = new MyList<>();
        list.pushBack("data_1");
        list.pushBack("data_2");
        list.pushBack("data_3");
        list.pushFront("data_4");
        assertEquals("data_3", list.popBack());
    }

    @Test
    public void testCopy() {
        MyList<Integer> list_1 = new MyList<>();
        list_1.pushBack(10);
        list_1.pushBack(20);
        list_1.pushBack(30);
        list_1.pushBack(40);
        MyList<Integer> list_2 = new MyList<>(list_1);
        for (int i = 0; i < list_2.getSize(); i++)
            assertEquals(list_1.popBack(), list_2.popBack());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testException_1() {
        MyList<String> list = new MyList<>();
        list.popBack();
    }

    @Test(expected = NoSuchElementException.class)
    public void testException_2() {
        MyList<String> list = new MyList<>();
        list.pushBack("data_1");
        list.pushBack("data_2");
        list.pushBack("data_3");
        list.del("data_10");
    }
}