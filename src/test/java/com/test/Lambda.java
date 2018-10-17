package com.test;

import com.test.po.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 学习使用Lambda表达式
 *
 * @author zhangxuan
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
        Employee emp = new Employee("张三", 18, 3333.33);
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
        Supplier<Employee> sup = () -> new Employee("张三", 18, 3333.33);
        Employee emp = sup.get();
        System.out.println(emp);

        // 构造器引用方式
        Supplier<Employee> sup1 = () -> new Employee("张三", 18, 3333.33);
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

    /**
     * Lambda遍历数组
     */
    @Test
    public void test18(){


        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

    // 以前的循环方式
        for (String player : players) {
            System.out.print(player + "; ");
        }

        players.forEach(t -> System.out.println(t+";"));
        System.out.println("-------------");

        players.forEach(System.out::println);

    }

    /**
     * StreamAPI
     *  数据渠道，用于操作数据源
     */

    /**
     *  创建Stream
     */
    @Test
    public void test19(){
        //// 1、 可以通过 Conllection 系列集合提供的顺序流 stream()
        // 或并行流 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        stream = list.parallelStream();

        //2、 通过 Arrays 中的静态方法 stream()获取数据流
        Integer integer[] = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(integer);

        //3、 通过 Stream 类中的静态方法 of()
        Stream<String> stream2 = Stream.of("aa","bb","cc");
        stream2.limit(3).forEach(System.out::println);
        System.out.println("--------");

        String str[] = new String[10];
        Stream<String> stream3 = Stream.of(str);
        stream3.limit(10).forEach(System.out::println);

        //4、 创建无限流-迭代
        System.out.println("xxxxxxxxxxxx");
        Stream<Integer>  stream4 = Stream.iterate(0,x -> x+2);
        stream4.limit(10).forEach(System.out::println);

        System.out.println("++++++++++++");
        // 5、 创建无限流-生成
        Stream.generate(() -> Math.random())
                .limit(5).forEach(System.out::println);

    }

    /**
     * Stream的中间操作
     * 筛选和切片
     */
    @Test
    public void test20(){
    List<Employee> emps = Arrays.asList(
            new Employee(18, "张三"),
            new Employee(38, "李四"),
            new Employee(50, "王五"),
            new Employee(18, "赵六"),
            new Employee(28, "田七")
    );
        /**
         * filter
         * filter接收Lambda，从流中排除某些元素
         */
       Stream<Employee> stream = emps.stream()
               .filter((employee) -> {
                   return employee.getAge()>35;
               });

       //终止操作：一次性执行全部内容，即“惰性求值”
       stream.forEach(System.out::println);//打印大于35的对象
        System.out.println("----------------");

        // limit-截断流， 使其元素不超过给定数量
        emps.stream().filter(e -> e.getAge() >18).limit(2).forEach(System.out::println);

        System.out.println("===========");
        // skip-跳过元素， 返回一个扔掉了前 n 个元素的流， 若流中元素不足 n 个， 则
//        返回一个空流。
        emps.stream().filter(e -> e.getAge() >15).skip(2).forEach(System.out::println);

        System.out.println("+++++++++++++");

        // distinct-筛选， 通过流所生产元素的 hashCode()和 equals()去除重复元素
        // 筛选，过滤指定内容
        emps.stream().filter(e->e.getAge() >18).distinct().forEach(System.out::println);

        System.out.println("XXXXXXXXXXXXXXXXXXXX");

        //接收一盒函数作为参数，改函数会被应用到每个元素上，并将其映射成一个新的元素
        emps.stream().map(Employee::getName).forEach(System.out::println);
    }

    /**
     *  映射 MAP
     *  map-接收 Lambda， 将元素转换成其他形式或提取信息。
     *  接收一个函数作为参数，
     *  该函数会被应用到每个元素上，
     *  并将其映射成一个新的元素。
     *
     */
    @Test
    public void test21(){
        List<String> list = Arrays.asList("aa","bb","cc","dd");

        list.stream().map(str-> str.toUpperCase()).forEach(System.out::println);

        System.out.println(")))))))))))))))))))))))");

    }


    /**
     *  自然排序
     *  sorted（Comparable）
     *  数字，大写字母，字符，小写字母，
     */
    @Test
    public void test22() {
        List<String> list = Arrays.asList("cc", "aa", "BB", "ee", "dd","11","@");

        list.stream().sorted()
                .forEach(System.out::println);
    }

    /**
     * 定制排序
     * sorted（Comparator）
     */
    @Test
    public void test23() {
        List<Employee> emps = Arrays.asList(
                new Employee(18, "张三"),
                new Employee(38, "李四"),
                new Employee(50, "王五"),
                new Employee(16, "赵六"),
                new Employee(28, "田七")
        );

        emps.stream().sorted((e1,e2)->{
            if (e1.getAge().equals(e2.getAge())){
                return e1.getName().compareTo(e1.getName());
            } else {
                return e1.getAge().compareTo(e2.getAge());
            }
        }).forEach(System.out::println);

        System.out.println();
        // 需求： 计算所有员工的年龄总和
        Optional<Integer> op = emps.stream()
                .map(Employee::getAge)
                .reduce(Integer::sum);
        System.out.println(op.get());
    }


    /**
     *
     * 归约
     */
    @Test
    public void test24() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);// 0 代表数字 0， 不是下标
        System.out.println(sum);
        System.out.println("-----------------------------");

    }

    /**
     * // 收集-将流转换为其他形式， 接收一个 Collertor 接口的实现， 用于给 Stream 中元素做汇总
     * 的方法
     */
    @Test
    public void test25(){

        List<Employee> emps = Arrays.asList(
                new Employee(18, "张三"),
                new Employee(38, "李四"),
                new Employee(50, "王五"),
                new Employee(18, "赵六"),
                new Employee(28, "田七")
        );

        List<String> list = emps.stream().map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("----");
//        emps.forEach(System.out::println);

        //set  把流中的所有元素收集到set中，删除重复项
        Set<Integer> set = emps.stream().map(Employee::getAge)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);

        System.out.println("**********************");

        // Map-把流中所有元素收集到 Map 中， 当出现相同的 key 时会抛异常
        Map<String,Integer> map = emps.stream()
                .collect(Collectors.toMap(Employee::getName,Employee::getAge));
        System.out.println(map);

        //员工人数
        Long collect = emps.stream().collect(Collectors.counting());
        System.out.println(collect);
        
    }


}