package com.test;

/**
 * @auther zhangxuan
 * @date 2018/10/12
 * @time 20:31
 */
@FunctionalInterface
public interface MyFunction<T> {
    public T getValue(T o);
}
