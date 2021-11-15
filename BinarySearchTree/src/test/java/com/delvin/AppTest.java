package com.delvin;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AppTest 
{
    @Test
    public void testAdd_1()
    {
        BST<String> bst = new BST<>();
        bst.insert("data_1");
        bst.insert("data_11");
        bst.insert("data_111");
        assertEquals("data_1", bst.search("data_1"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testAdd_2()
    {
        BST<String> bst = new BST<>();
        bst.insert("data_1");
        bst.insert("data_11");
        bst.insert("data_111");
        assertEquals("data_1", bst.search("some_value"));
    }

    @Test
    public void testCopy_size(){
        Random random = new Random();
        BST<Integer> bst_1 = new BST<>();
        for(int i = 0; i < 10_000; i++)
            bst_1.insert(random.nextInt(10_000));
        BST<Integer> bst_2 = new BST<>(bst_1);
        assertEquals(bst_1.size(), bst_2.size());
    }

    @Test
    public void testCopy_elements(){
        Random random = new Random();
        List<Integer> data = new ArrayList<>();
        BST<Integer> bst_1 = new BST<>();
        for(int i = 0; i < 10_000; i++)
        {
            data.add(random.nextInt());
            bst_1.insert(data.get(i));
        }
        BST<Integer> bst_2 = new BST<>(bst_1);
        for(int i = 0; i < data.size(); i++)
            assertEquals(bst_1.search(data.get(i)), bst_2.search(data.get(i)));
    }

    @Test
    public void testClear_1(){
        Random random = new Random();
        BST<Integer> bst = new BST<>();
        for(int i = 0; i < 10_000; i++)
            bst.insert(random.nextInt());
        bst.clear();
        assertEquals(0, bst.size()); 
    }

    @Test(expected = NoSuchElementException.class)
    public void testClear_2(){
        Random random = new Random();
        BST<Integer> bst = new BST<>();
        for(int i = 0; i < 10_000; i++)
            bst.insert(random.nextInt(1_000));
        bst.clear();
        bst.search(random.nextInt(1_000));
    }
}