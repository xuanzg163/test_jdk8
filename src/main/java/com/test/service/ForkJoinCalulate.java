package com.test.service;

import java.util.concurrent.RecursiveTask;

/**
 * 学习使用fork/join 框架
 *
 * @author zhangxuan
 * @date 2018/10/17
 * @time 14:36
 */

/**
 * 累加运算测试
 */
public class ForkJoinCalulate extends RecursiveTask<Long> {

    private static final long serialVersionUID =  6262874164889171856L;

    private long start;
    private long end;

    //临界值
    private static final long THRESHOLE = 10000L;

    public ForkJoinCalulate() {
    }

    public ForkJoinCalulate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if (length <= THRESHOLE){
            long sum = 0L;

            for (long i = start; i < end; i++) {
                sum += i;
            }
        return sum;
        } else {
            long middle = (start + end) / 2;

            ForkJoinCalulate left = new ForkJoinCalulate(start,middle);
            left.fork();//拆分子任务,压入线程队列

            ForkJoinCalulate right = new ForkJoinCalulate(middle + 1, end);
            right.fork();//拆分子任务，压入线程队列

            //返回执行结果
            return left.join() + right.join();
        }
    }
}
