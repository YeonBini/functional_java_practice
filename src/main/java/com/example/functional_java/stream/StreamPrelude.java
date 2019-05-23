package com.example.functional_java.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StreamPrelude {

    public static void main(String[] args) {
        final int abs1 = Math.abs(-1);
        final int abs2 = Math.abs(1);

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(map(numbers, i -> i * 2));
        System.out.println(map(numbers, null));
        System.out.println(map(numbers, i -> i));
        System.out.println(map(numbers, Function.<Integer>identity()));

    }

    private static <T, R> List<R> map(final List<T> list, final Function<T, R> mapper) {
        final Function<T, R> function;
        if(mapper != null) {
            function = mapper;
        } else {
            function = t -> (R) t;
        }

        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }
}
