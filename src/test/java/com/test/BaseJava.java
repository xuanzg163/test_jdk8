package com.test;

import org.junit.Test;

/**
 * @author zhangxuan
 * @date 2018/11/5
 * @time 8:24
 */

public class BaseJava {

    @Test
    public void test01() {

        int x = 6;
        int y = 8;

        boolean a;
        boolean b;

//        a = x > y || ++x == --y; //x=y=7（逻辑或，左边为true 右边才不会计算）
//        System.out.println(a+"   "+x+" ,  "+y);

        b = x > y && ++x == --y; //x=6,y=8 (短路与，左边为false，右边不会计算)
        System.out.println(b + "   " + x + " ,  " + y);
    }

}
