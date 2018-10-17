package com.test.po;

import com.test.inter.hunman;
import com.test.inter.hunman2;

/**
 * @author zhangxuan
 * @date 2018/10/17
 * @time 17:01
 */

public class Student implements hunman, hunman2 {
     void run(){
//        System.out.println("我才是被调用的");
    }
    public static void main(String[] args){
        Student student = new Student();

        student.run();
        hunman.run();
    }
}
