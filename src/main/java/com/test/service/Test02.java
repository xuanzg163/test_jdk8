package com.test.service;

/**
 * @author zhangxuan
 * @date 2018/11/5
 * @time 8:57
 */

public class Test02 {

    /**
     * final 修饰的变量需要初始化
     */
    final int I = 0;

    /**
     *  测试String特性
     * @param str
     */
    public static void changeStr(String str) {
        str = "welcome";
    }

    public static void main(String[] args){

        String str = "1234";
        changeStr(str);

        //输出 1234
        System.out.println(str);
    }
}
