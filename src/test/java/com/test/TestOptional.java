package com.test;

import com.test.po.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional<T>类（java.util.Optional） 是一个容器类，
 * 代表一个值存在或不存在，
 * 原来用null 表示一个值不存在，
 * 现在 Optional 可以更好的表达这个概念。
 * 并且可以避免空指针异常。
 *
 * @author zhangxuan
 * @date 2018/10/17
 * @time 15:34
 */

public class TestOptional {

    /**
     * Optional 容器类的常用方法：
     * of(T value)： 创建一个 Optional 实例
     */
    @Test
    public void test01() {

        Optional<Employee> op = Optional.of(new Employee());

        //Employee{Name='null', Age=null}
        System.out.println(op.get());

    }

    /**
     * *
     * empty()： 创建一个空的 Optional 实例
     *
     */
    @Test
    public void test02() {
        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());
    }

    @Test
    public void test03(){
        Optional<Employee> op = Optional.ofNullable(null);
//        System.out.println(op.get());

        //判断是否包含空值
        System.out.println(op.isPresent());

        Employee emp = op.orElse(new Employee(11,"zhangsan"));
        System.out.println(emp);

        //如果调用对象包含值， 返回该值， 否则返回 other 获取的值
        Employee emp1 = op.orElseGet(() -> new Employee());
        System.out.println(emp1);

    }

    /**
     * map(Function mapper)： 如果有值对其处理， 并返回处理后的 Optional， 否则返回
     * Optional.empty()
     * * flatMap(Function mapper)： 与 map 类似， 要求返回值必须是 Optional
     */
    @Test
    public void test4() {
        Optional<Employee> op = Optional.ofNullable(new Employee("张三", 18, 333.33));
        Optional<String> str = op.map((e) -> e.getName());

        System.out.println(str.get());

        Optional<String> str1 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(str1.get());
    }
}
