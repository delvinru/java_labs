package com.delvin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.security.KeyException;
import java.util.*;

import org.junit.Test;

public class AppTest {
    @Test
    public void testSearch() {
        BTreeMap<Integer, String> map = new BTreeMap<>(3);
        for (int i = 0; i < 1337; i++)
            map.put(i, UUID.randomUUID().toString());
        for (int i = 0; i < 1337; i++)
            assertTrue(map.search(i));
    }

    @Test
    public void testGetter() {
        BTreeMap<Integer, String> map = new BTreeMap<>(3);
        for (int i = 0; i < 1337; i++)
            map.put(i, UUID.randomUUID().toString());

        for (int i = 0; i < 1337; i++)
            assertNotNull(map.get(i));
    }

    @Test
    public void testClear() {
        BTreeMap<Integer, String> map = new BTreeMap<>(3);
        for (int i = 0; i < 1337; i++)
            map.put(i, UUID.randomUUID().toString());

        map.clear();
        assertTrue(map.empty());
    }

    @Test(expected = KeyException.class)
    public void testSetter() throws KeyException {
        BTreeMap<Integer, String> map = new BTreeMap<>(3);
        for (int i = 0; i < 1337; i++)
            map.put(i, UUID.randomUUID().toString());

        map.set(-1, "laksdfj");
    }

    @Test
    public void testSetGet() throws KeyException {
        BTreeMap<Integer, String> map = new BTreeMap<>(3);
        for (int i = 0; i < 1337; i++)
            map.put(i, UUID.randomUUID().toString());

        String test = "My super test string that definetly not UUID";
        map.set(555, test);
        assertEquals(test, map.get(555));
    }

    @Test
    public void testIterator() {
        int n = 100;
        BTreeMap<Integer, String> map = new BTreeMap<>(3);
        for (int i = 0; i < n; i++)
            map.put(i, UUID.randomUUID().toString());

        HashSet<Element<Integer, String>> set = new HashSet<>();
        for (Element<Integer, String> element : map)
            set.add(element);
        assertEquals(n, set.size());
    }
}