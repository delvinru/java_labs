package com.delvin;

public class App {
    public static void main(String[] args) {
        MyVector<Cat> vec = new MyVector<>();
        vec.push(new Cat("cat 1", 3));
        vec.push(new Cat("cat 2", 4));
        vec.push(new Cat("cat 3", 6));
        System.out.println(vec);
        vec.del(new Cat("cat 1", 3));
        System.out.println(vec);
    }
}
