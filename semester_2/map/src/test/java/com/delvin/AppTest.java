package com.delvin;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

public class AppTest {
    @Test
    public void testAppend_1() {
        Map<Integer, String> map = new Map<>();
        map.push(10, "data_1");
        map.push(20, "data_2");
        map.push(30, "data_3");
        map.push(1, "data_4");
        assertEquals(new Element<Integer, String>(10, "data_1"), map.get(10));
        assertEquals(new Element<Integer, String>(20, "data_2"), map.get(20));
        assertEquals(new Element<Integer, String>(30, "data_3"), map.get(30));
        assertEquals(new Element<Integer, String>(1, "data_4"), map.get(1));
    }

    @Test
    public void testAppend_2() {
        List<Element<Integer, String>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            list.add(new Element<Integer, String>(i, UUID.randomUUID().toString()));

        Map<Integer, String> map = new Map<>();
        for (Element<Integer, String> el : list)
            map.push(el);

        for (int i = 0; i < 100; i++)
            assertEquals(list.get(i), map.get(i));
    }

    @Test
    public void testIterator() {
        List<Element<Integer, String>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            list.add(new Element<Integer, String>(i, UUID.randomUUID().toString()));

        Map<Integer, String> map = new Map<>();
        for (Element<Integer, String> el : list)
            map.push(el);

        for (Element<Integer, String> element : map) {
            System.out.println(element);
        }
    }
}
