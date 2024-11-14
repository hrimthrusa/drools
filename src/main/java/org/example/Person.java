package org.example;


public class Person {
    private String name;
    private int age;
    private boolean adult;

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public Person (String name, int age) {
        this.name = name;
        this.age = age;
        this.adult = false;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isAdult() {
        return adult;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + ", adult=" + adult + '}';
    }
}
