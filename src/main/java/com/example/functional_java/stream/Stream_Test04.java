package com.example.functional_java.stream;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Stream_Test04 {

    public static void main(String[] args) {
        int n = 100;
        long startSequential = System.currentTimeMillis();
        System.out.println(sequentialSum(n));
        System.out.println("Seq timespent\t: " + (System.currentTimeMillis() -startSequential));

        long startParallel = System.currentTimeMillis();
        System.out.println(parallelSum(n));
        System.out.println("Parallel timespent\t: " + (System.currentTimeMillis() -startParallel));

    }

    private static void slowdown()  {
        try {
            TimeUnit.MILLISECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                     .limit(n)
                     .peek(i -> slowdown())
                     .reduce(Long::sum)
                     .get();
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i+1)
                     .limit(n)
                     .parallel()
                     .peek(i -> slowdown())
                     .reduce(Long::sum)
                     .get();
    }

}
