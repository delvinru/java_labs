package com.delvin;

import java.util.UUID;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        List<Element<Integer, String>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            list.add(new Element<Integer, String>(i * (new Random().nextInt(1337) - new Random().nextInt(100)), UUID.randomUUID().toString()));

        Map<Integer, String> map = new Map<>();
        for (Element<Integer, String> el : list)
            map.push(el);

        for (Element<Integer, String> element : map) {
            System.out.println(element);
        }
    }
}
