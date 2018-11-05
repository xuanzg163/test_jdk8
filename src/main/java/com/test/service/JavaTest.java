package com.test.service;

/**
 * @author zhangxuan
 * @date 2018/11/5
 * @time 9:19
 */

public class JavaTest {
    public static void main(String[] args){
        Hello obj = new Hello("hi");


    }


}

class Hello {
    public String msg = "hello";

    public Hello() {
    }

    public Hello(String msg) {
        this.msg = msg;
    }
}
