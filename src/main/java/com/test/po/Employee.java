package com.test.po;

/**
 * @auther zhangxuan
 * @date 2018/10/12
 * @time 23:24
 */

public class Employee {

    private String name;
    private Integer age;

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

    public Employee() {
    }

    public Employee(Integer age) {
        this.age = age;
    }

    public Employee( Integer age,String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Name='" + name + '\'' +
                ", Age=" + age +
                '}';
    }
}
