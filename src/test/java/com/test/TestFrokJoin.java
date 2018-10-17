package com.test;

import com.test.service.ForkJoinCalulate;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author zhangxuan
 * @date 2018/10/17
 * @time 14:54
 */

public class TestFrokJoin {
    /**
     * 测试ForkJoin框架
     * 数据比较大使用比较好，数据越大效率越高
     */

    @Test
    public void test1() {

        //统计程序运行时间
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Long> task = new ForkJoinCalulate(0,1000000000L);

        Long sum = pool.invoke(task);

        Instant end = Instant.now();

        System.out.println("ForkJoin耗费时间为：" + Duration.between(start,end).toMillis());
    }

    /**
     * 普通for 数据小使用比较好，省去了拆分时间
     */
    @Test
    public void test02() {
        Instant start = Instant.now();
        long sum = 0L;

        for (long i = 0; i < 1000000000L; i++) {
            sum += i;
        }

        Instant end = Instant.now();
        System.out.println("普通for循环耗费时间为：" +Duration.between(start,end).toMillis());
    }

    /**
     * 顺序流
     */
    @Test
    public void test03() {
        Instant start = Instant.now();
        LongStream.rangeClosed(0,1000000000L).sequential().reduce(0,Long::sum);
        Instant end = Instant.now();
        System.out.println("顺序流运行时间： "+Duration.between(start,end).toMillis());
    }

    /**
     * 并行流
     */
    @Test
    public void test04() {
        Instant start = Instant.now();
        LongStream.rangeClosed(0,1000000000L)
                .parallel()//并行流
                .reduce(0,Long::sum);
        Instant end = Instant.now();
        System.out.println("并行流运行时间： "+Duration.between(start,end).toMillis());
    }


}
