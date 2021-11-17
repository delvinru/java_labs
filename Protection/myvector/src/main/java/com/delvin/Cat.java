package com.delvin;

public class Cat {
    private int age;
    private String name;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object cat) {
        if (this.age == ((Cat) cat).age && this.name.equals(((Cat) cat).name))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Cat [age=" + age + ", name=" + name + "]";
    }
}
