package com.delvin;

import static org.junit.Assert.assertEquals;
import java.util.Collections;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class AppTest 
{
    @Test
    public void test_1()
    {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        List<Integer> test = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            int el = new Random().nextInt(1337);
            test.add(el);
            queue.push(el);
        }
        Collections.sort(test);
        Collections.reverse(test);
        assertEquals(test.get(0), queue.pop());
    }

    @Test
    public void test_2()
    {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        List<Integer> test = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            int el = new Random().nextInt(1337);
            test.add(el);
            queue.push(el);
        }
        Collections.sort(test);
        Collections.reverse(test);
        assertEquals(test.get(0), queue.getMax());
    }

    @Test
    public void test_3()
    {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < 100; i++){
            int el = new Random().nextInt(1337);
            queue.push(el);
        }
        queue.clear();
        assertEquals(0, queue.getSize());
    }

    @Test
    public void test_4()
    {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < 100; i++){
            int el = new Random().nextInt(1337);
            queue.push(el);
        }
        queue.clear();
        assertEquals(true, queue.isEmpty());
    }

    @Test
    public void test_5()
    {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < 100; i++){
            int el = new Random().nextInt(1337);
            queue.push(el);
        }
        assertEquals(false, queue.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void test_6()
    {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.push(null);
    }
}
