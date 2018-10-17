package com.test.po;

public class Employee {

    private String name;
    private Integer age;
    private Double saler;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Employee(String name, int age, double saler) {
        this.age = age;
        this.name = name;
        this.saler = saler;
    }

    public Employee(Integer age) {
        this.age = age;
    }

    public Employee( Integer age,String name) {
        this.age = age;
        this.name = name;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Name='" + name + '\'' +
                ", Age=" + age +
                '}';
    }
}
