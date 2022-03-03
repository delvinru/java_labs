package com.delvin;

import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;

public class AppTest {
    @Test
    public void testPutGet() {
        HashMap<Integer, String> map = new HashMap<>();
        List<String> test = new ArrayList<>();
        for (int i = 0; i < 1337; i++) {
            String uuid = UUID.randomUUID().toString();
            map.put(i, uuid);
            test.add(uuid);
        }
        for(int i = 0; i < 1337; i++)
            assertEquals(test.get(i), map.get(i));
    }

    @Test
    public void testClear(){
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 1337; i++)
            map.put(i, UUID.randomUUID().toString());
        map.clear();
        assertEquals(true, map.isEmpty());
        assertEquals(0, (int)map.getLoadFactor());
    }

    @Test
    public void testRemove(){
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 1337; i++)
            map.put(i, UUID.randomUUID().toString());
        map.remove(337);
        assertEquals(null, map.get(337));
    }

    @Test
    public void testGetSize(){
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 1337; i++)
            map.put(i, UUID.randomUUID().toString());
        assertEquals(1337, map.size());
    }
}
