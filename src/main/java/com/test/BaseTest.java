package com.test;

/**
 * @author zhangxuan
 * @date 2018/12/4
 * @time 22:47
 */

public class BaseTest {
    public static void main(String[] args) {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;

        //false
        System.out.println(s1 == s2);
        //true
        System.out.println(s1 == s5);
        //false
        System.out.println(s1 == s6);
        //true
        System.out.println(s1 == s6.intern());
        //false
        System.out.println(s2 == s2.intern());
    }
}
