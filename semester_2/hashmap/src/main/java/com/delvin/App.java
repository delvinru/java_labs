package com.delvin;

import java.util.List;
import java.util.ArrayList;

import java.util.UUID;

public class App {
    public static void main(String[] args) {
        int n = 40;
        HashMap<Integer, String> map = new HashMap<>();
        List<String> test = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String uuid = UUID.randomUUID().toString();
            map.put(i, uuid);
            test.add(uuid);
        }
        for(int i = 0; i < n; i++){
            System.out.println(i + " " + map.get(i));
        }
        // HashMap<Integer, String> map = new HashMap<>();

        // int n = 100;

        // for (int i = 0; i < n; i++){
        // map.put(i, UUID.randomUUID().toString());
        // System.out.println(map.getLoadFactor());
        // }

        // // for (int i = 0; i < n; i++)
        // // System.out.println(map.get(i));

        System.out.println(map.size());
        System.out.println(map.getCapacity());
    }
}
