package com.test;

import com.test.po.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.*;
import java.util.function.*;

/**
 * 学习使用Lambda表达式
 *
 * @auther zhangxuan
 * @date 2018/10/12
 * @time 19:25
 */

public class Lambda {

    /**
     * 1
     * lambda 表达式的语法
     * 无参数，无返回值
     */
    @Test
    public void test01() {
        int num = 0;

        /**
         * 2
         * 类实现多线程继承并重写runnable run方法
         */

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("hello lambda-----" + num);
            }
        };
        runnable.run();

        System.out.println("---------");

        // 参数列表 -> Lambda体
        Runnable r = () -> System.out.println("hello lambda!!!-----" + num);
        r.run();
    }

    /**
     * 3
     * 有一个参数，无返回值
     */
    @Test
    public void test02() {
//        Consumer<String> consumer = (t) -> System.out.println(t);
        //若只有一个参数，括号可以省略
        Consumer<String> consumer = t -> System.out.println(t);
        consumer.accept("hello lambda!!!");
    }


    /**
     * 4
     * 有2个以上参数，有返回值。并且Lambda体有多条语句
     */
    @Test
    public void test03() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("hello lambda !!!");
            return Integer.compare(x, y);
        };
        System.out.println(comparator.compare(1, 2));
    }

    /**
     * 5
     * 若Lambda体中只有一条语句，return和大括号都可以不写
     */
    @Test
    public void test04() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator.compare(1, 2));
    }

    /**
     * 6
     * Lambda 表达式的参数列表的数据类型可以省略不写， 因为 JVM 编译器通过上下文推断出
     * 数据类型， 即“类型推断”
     */
    @Test
    public void test05() {
//        Comparator<Integer> comparator = (Integer x, Integer y) -> Integer.compare(x, y);
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator.compare(1, 2));
        show(new HashMap<>());
    }

    public void show(Map<String, Integer> map) {

    }

    /**
     * 使用函数式接口
     */
    @Test
    public void test06(){
        Integer result = operation(100, x -> x*x);
        System.out.println(result);

        result = operation(200, y -> y - 100);
        System.out.println(result);
    }

    public Integer operation(Integer num,MyFunction<Integer> mf){
        return mf.getValue(num);
    }


    /**
     * 四大内置核心函数式接口
     */

    /*
     * Consumer<T> 消费型接口
     * void accept(T t);
     */
    @Test
    public void test07(){
        happy(100.111,m -> System.out.println("xxxxx"+m+" $"));

    }
    public void happy(double money,Consumer<Double> consumer){
        consumer.accept(money);
    }

    /*
     * Supplier<T> 供给型接口
     * T get();
     */
// 需求： 产生指定个数的整数， 并放入集合中
    @Test
    public void test08(){
        //无参数
        List<Integer> numList = getNumList(10,() -> (int)(Math.random()*100));

        for (Integer integer:numList
             ) {
            System.out.println(integer);
        }
    }

    public List<Integer> getNumList(int num, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();

        for (int i = 0 ;i<num; i++){
            Integer n = supplier.get();
            list.add(n);
        }
        return list;
    }

    /*
     * Function<T, R> 函数型接口
     * R apply(T t);
     */
// 需求： 用于处理字符串

    @Test
    public void test09(){

        String newString = strHandler(" helleo ",string ->string.trim());
        System.out.println(newString);

        newString = strHandler("hello", string -> string.substring(2, 4));
        System.out.println(newString);
    }

    public String strHandler(String str, Function<String,String> fun) {
        return fun.apply(str);
    }

    /*
     * Predicate<T> 断言型接口
     * boolean test(T t);
     */
// 需求： 将满足条件的字符串， 放入集合中
    @Test
    public void test10(){
        List<String> list = Arrays.asList("hello", "java", "1.8", "Lambda", "!!!");
        List<String> strList = filterStr(list, (s) -> s.length() > 3);
        for (String string : strList) {
            System.out.println(string);
        }
    }

    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strList = new ArrayList<>();
        for (String string : list) {
            if(pre.test(string)) {
                strList.add(string);
            }
        }
        return strList;
    }

    //对象：：实例方法名
    @Test
    public void test11(){
//        Consumer<String> consumer = t -> System.out.println(t);
//        consumer.accept("first");//一般方法

        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;//对象：：实例方法名
        con1.accept("wotmshebao");

        Consumer<String> con2 = System.out::println;
        con2.accept("nihao");
    }

    @Test
    public void test12(){
        Employee emp = new Employee();
        emp.setName("王二");
        emp.setAge(11);
        Supplier<String> sup = () -> emp.getName();
        String str = sup.get();
        System.out.println(str);

        Supplier<Integer> sup2 = emp::getAge;
        Integer age = sup2.get();
        System.out.println(age);
    }

    // 类::静态方法名
    @Test
    public void test13() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        System.out.println(com.compare(1,2));

        //类：：静态方法
        Comparator<Integer> com1 = Integer::compare;
        System.out.println(com1.compare(2,1));
    }

    // 类::实例方法名
    @Test
    public void test14() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        System.out.println(bp.equals(bp));

        BiPredicate<String, String> bp1 = String::equals;
        System.out.println(bp1.equals(bp));

    }

    /**
     * 构造器引用
     * 空构造器
     */
    @Test
    public void test15(){
        Supplier<Employee> sup = () -> new Employee();
        Employee emp = sup.get();
        System.out.println(emp);

        // 构造器引用方式
        Supplier<Employee> sup1 = Employee::new;
        Employee emp1 = sup.get();
        System.out.println(emp1);
    }

    /**
     * 构造器引用
     * 带参构造
     */
    @Test
    public void test16() {
        Function<Integer, Employee> func = x -> new Employee(x);
        Employee employee = func.apply(20);
        System.out.println(employee);

// 需要调用的构造器的参数列表要与函数式接口中抽象方法参数列表保持一致
        Function<Integer, Employee> func2 = Employee::new;
        Employee emp1 = func2.apply(18);
        System.out.println(emp1);

        BiFunction<Integer, String, Employee> bf = Employee::new;
        Employee emp2 = bf.apply(20, "李三");
        System.out.println(emp2);
    }

    /**
     * 数组引用
     *
     */
    @Test
    public void test17() {
        Function<Integer, String[]> func = (x) -> new String[x];
        String strs[] = func.apply(10);
        System.out.println(strs.length);

        Function<Integer, String[]> func2 = String[]::new;
        String strs2[] = func2.apply(20);
        System.out.println(strs2.length);
    }

}
