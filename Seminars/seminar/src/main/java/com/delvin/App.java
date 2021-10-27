package com.delvin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        if (args.length == 0 || args[0].equals("-h")) {
            System.out.println("Usage: ./App <filename>");
            System.exit(1);
        }
        String filename = args[0];
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("[-] File not exists");
            System.exit(1);
        }
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            HashMap<Integer, Double> hashMap = getFrequence(fileContent);
            for (Integer key : hashMap.keySet())
                System.out.println(Integer.toHexString(key) + ":" + hashMap.get(key));
        } catch (IOException e) {
            System.out.println("[-] Error while reading file");
            System.exit(1);
        }
    }

    public static HashMap<Integer, Double> getFrequence(byte[] fileContent) {
        HashMap<Integer, Double> hashMap = new HashMap<>();
        Arrays.sort(fileContent);
        for (byte element : fileContent) {
            if (hashMap.containsKey(element & 0xff))
                hashMap.put(element & 0xff, hashMap.get(element & 0xff) + 1);
            else
                hashMap.put(element & 0xff, 1d);
        }
        int n = fileContent.length;
        for (Integer key : hashMap.keySet())
            hashMap.put(key, hashMap.get(key) / n);
        return hashMap;
    }
}
