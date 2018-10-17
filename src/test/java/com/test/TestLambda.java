package com.test;

import com.test.po.Person;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author zhangxuan
 * @date 2018/10/17
 * @time 17:28
 */

public class TestLambda {

    /**
     * list 的遍历
     */
    @Test
    public void test01() {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer", "Roger Federer",
                "Andy Murray", "Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> lists = Arrays.asList(atp);

        //通常的遍历方式
        for (String item : lists
        ) {
            System.out.println(item);
        }

        //分割
        System.out.println("===========");

        //JDK8 lambda
        lists.forEach(t -> System.out.println(t + ","));

        //分隔符
        System.out.println("++++++++++++++++++");
        //JDK8 双冒号
        lists.forEach(System.out::println);

    }

    /**
     * 匿名内部类
     */
    @Test
    public void test02() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hi runnable");
            }
        }).start();

        //JDK8
        new Thread(
                () -> System.out.println("hi ru")
        ).start();

        // 2.1使用匿名内部类
        Runnable race1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        };

        //JDK8
        Runnable runnable = () -> System.out.println("hihih");

        //调用run方法
        runnable.run();
        race1.run();

    }

    /**
     * 排序
     */
    @Test
    public void test03() {
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};
        List<String> lists = Arrays.asList(players);

        // 1.1 使用匿名内部类根据 name 排序 players
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });

        System.out.println(players);
        // 1.2 使用 lambda expression 排序 players
        Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
        Arrays.sort(players, sortByName);

        System.out.println("排序");

        //JDK8
        lists.forEach(System.out::println);
        System.out.println("========");
        lists.forEach(t -> System.out.println(t + " "));

    }

    /**
     * Stream
     */
    @Test
    public void test04() {

        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };

        /**
         * 排序
         */
        javaProgrammers.forEach(t -> System.out.println(t.getFirstName()));
        System.out.println("+++++++++++");

        javaProgrammers.forEach(System.out::println);

        System.out.println("加工资前");
        javaProgrammers.forEach(t -> System.out.println(t.getSalary()));
        Consumer<Person> giveRise = t -> t.setSalary(t.getSalary() / 100 * 5 + t.getSalary());


        System.out.println("加工资后");
        //方法调用
        javaProgrammers.forEach(giveRise);
        javaProgrammers.forEach(t -> System.out.println(t.getSalary()));

        /**
         * 过滤
         */
        System.out.println("下面是月薪超过 $1,400 的PHP程序员:");
        phpProgrammers.stream()
                .filter((p) -> (p.getSalary() > 1400))
                .forEach((p) -> System.out.println(p.getFirstName()));

        System.out.println("下面是月薪超过 $1,500 的JAVA程序员:");
        javaProgrammers.stream().filter(t -> (t.getSalary() > 1500))
                .forEach(t -> System.out.println(t.getFirstName()));

        System.out.println("下面是age超过 22 与工资过1800 的JAVA程序员: 只要2位");
        javaProgrammers.stream().filter(
                t -> (t.getAge() > 30 && t.getSalary() > 1800)//多个过滤条件
        ).limit(2).forEach(t -> System.out.println(t.getFirstName()));


        System.out.println("根据 name 排序,并显示前5个 Java programmers:");
        List<Person> sortedJavaProgrammers = javaProgrammers
                .stream()
                .sorted((p, p2) -> (p.getFirstName().compareTo(p2.getFirstName())))
                .limit(5)
                .collect(toList());

        sortedJavaProgrammers.forEach((p) ->
                System.out.println(p.getFirstName()));

        System.out.println("根据 salary 排序 Java programmers:");

        List<Person> collect = javaProgrammers.stream().sorted((p1, p2) -> (p1.getSalary() - p2.getSalary()))
                .collect(toList());
        collect.forEach(t -> System.out.println(t.getFirstName()));

        System.out.println("工资最低的 Java programmer:");
        Person pers = javaProgrammers
                .stream()
                .min((p1, p2) -> (p1.getSalary() - p2.getSalary()))
                .get();

        System.out.printf("Name: %s %s; Salary: $%,d.", pers.getFirstName(), pers.getLastName(), pers.getSalary());

        System.out.println("工资最高的 Java programmer:");
        Person person = javaProgrammers
                .stream()
                .max((p, p2) -> (p.getSalary() - p2.getSalary()))
                .get();

        System.out.printf("Name: %s %s; Salary: $%,d.", person.getFirstName(), person.getLastName(), person.getSalary());


        /**
         * map
         */
        System.out.println("将 Java programmers 的 first name 存放到 Set:");
        Set<String> firstName = javaProgrammers.stream()
                .map(Person::getFirstName).collect(Collectors.toSet());
        firstName.forEach(System.out::println);

        /**
         * 并行
         */
        System.out.println("计算付给 Java programmers 的所有money:");
        int sum = javaProgrammers.parallelStream()
                .mapToInt(t -> t.getSalary())
                .sum();
        System.out.println("计算付给 Java programmers 的所有money:" + sum);

    }

    /**
     * getMax,
     * getMin,
     * getSum,
     * getAverage,
     */
    @Test
    public void test05() {
        //计算 count, min, max, sum, and average for numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntSummaryStatistics intSummaryStatistics = numbers
                .stream()
                .mapToInt(x -> x)
                .summaryStatistics();

        System.out.println("List中最大的数字 : " + intSummaryStatistics.getMax());
        System.out.println("List中最小的数字 : " + intSummaryStatistics.getMin());
        System.out.println("所有数字的总和   : " + intSummaryStatistics.getSum());
        System.out.println("所有数字的平均值 : " + intSummaryStatistics.getAverage());


    }
}
