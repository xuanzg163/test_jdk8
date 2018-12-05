package com.test;

import java.time.LocalDateTime;

/**
 * @author zhangxuan
 * @date 2018/12/5
 * @time 10:59
 */
public class ShowTimeDate {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.minusDays(1);
        int year = now.getYear();
        System.out.println(year);
        System.out.println(localDateTime);
    }
}
