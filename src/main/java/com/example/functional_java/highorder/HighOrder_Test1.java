package com.example.functional_java.highorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class HighOrder_Test1 {

    public static void main(String[] args) {

        Function<Function<Integer, String>, String> function1
                = g -> g.apply(10);

        System.out.println("function1 example : " +
                function1.apply(i -> "#" + i));


        Function<Integer, Function<Integer, Integer>> function2
                = i -> (i2 -> i + i2);

        System.out.println("function2 example : "
                + function2.apply(1).apply(9));

        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        final List<String> result1 = map(list, i -> "#" + i);
        System.out.println("result1 : " + result1);

        // stream도 high order function 사용
        list.stream()
                .map(i -> "#" + i)
                .collect(toList());

    }

    private static <R, T> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }

}
