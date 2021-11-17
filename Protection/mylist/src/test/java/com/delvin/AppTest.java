package com.delvin;

import java.util.NoSuchElementException;

import org.junit.Test;

public class AppTest {
    @Test(expected = NoSuchElementException.class)
    public void testDelete() {
        MyList<String> list = new MyList<>();
        list.pushFront("data_1");
        list.pushFront("data_2");
        list.pushFront("data_3");
        list.del("data_1337");
    }
}
