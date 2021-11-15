package com.delvin;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {
    @Test
    public void testAdd_1() {
        MyVector<String> vec = new MyVector<String>();
        vec.push("data_1");
        vec.push("data_2");
        vec.push("data_3");
        assertEquals("data_1", vec.indexOf(0));
        assertEquals("data_2", vec.indexOf(1));
        assertEquals("data_3", vec.indexOf(2));
    }

    @Test
    public void testAdd_2() {
        MyVector<String> vec = new MyVector<String>(0);
        for (int i = 0; i < 100; i++)
            vec.push("data_" + i);
        assertEquals(100, vec.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAdd_3() {
        MyVector<String> vec = new MyVector<String>(0);
        vec.insertAt(20, "test");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAdd_4() {
        MyVector<String> vec = new MyVector<String>(0);
        vec.insertAt(-20, "test");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOf_1() {
        MyVector<Integer> vec = new MyVector<>();
        vec.push(10);
        vec.insertAt(10, 0x1337);
        vec.indexOf(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOf_2() {
        MyVector<Integer> vec = new MyVector<>(0);
        vec.push(10);
        vec.insertAt(10, 0x1337);
        vec.indexOf(0x100);
    }

    @Test
    public void testIndexOf_3(){
        MyVector<String> vec = new MyVector<>();
        vec.push("data_1");
        vec.push("data_2");
        vec.insertAt(0x20, "expected");
        assertEquals("expected", vec.indexOf(0x20));
    }

    @Test
    public void testCopy(){
        MyVector<String> vec_1 = new MyVector<>();
        for(int i = 0; i < 100; i++)
            vec_1.insertAt(i, "data_" + i);
        MyVector<String> vec_2 = new MyVector<>(vec_1);
        for(int i = 0; i < vec_2.size(); i++)
            assertEquals(vec_1.indexOf(i), vec_2.indexOf(i));
    }
}
