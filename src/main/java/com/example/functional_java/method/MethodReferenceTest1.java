package com.example.functional_java.method;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class MethodReferenceTest1 {
    public static void main(String[] args) {

        // void type
        Arrays.asList(1, 2, 3, 4, 5)
                .forEach(System.out::println);

        // int type
        System.out.println(
                Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23.0"), new BigDecimal("5"))
                        .stream()
//                .sorted(BigDecimalUtil::compare)
                        .sorted(BigDecimal::compareTo)
                        .collect(toList())
        );


        System.out.println(
                Arrays.asList("a", "b", "c", "d", "e")
                        .stream()
//                        .anyMatch(x -> x.equals("c"))
                        .anyMatch("c"::equals)
        );

        System.out.println("============== Lecture 3 =================");
        methodReference03();

    }

    private static void methodReference03() {
        /* First Class Function */
        // Function can be passed as a parameter to another function
        System.out.println(
                testFirstClassFunction01(3, i -> String.valueOf(i * 2))
        );
        // Using Method Reference
        System.out.println(
                testFirstClassFunction01(3, MethodReferenceTest1::doubleThenToString)
        );

        /**
         * Function can be return as a result of another function
         */
        //using Lambda expression
        Function f1 = getDoubleThenToStringUsingLambdaExp();
        System.out.println(
                f1.apply(3)
        );

        //using method
        final Function<Integer, String> fmr = getDoubleThenToStringUsingMethodExp();
        final String resultFromfmr = fmr.apply(3);
        System.out.println(resultFromfmr);


        /**
         * Function can be stored in the data structure
         */
        final List<Function<Integer, String>> fsl = Arrays.asList(i -> String.valueOf(i * 2),
                i -> String.valueOf(i * 3));

        for(final Function f : fsl) {
            System.out.println(f.apply(2));
        }

        final List<Function<Integer, String>> fsm = Arrays.asList(
                MethodReferenceTest1::doubleThenToString,
                MethodReferenceTest1::tripleThenToString);
        for(final Function f : fsm) {
            System.out.println(f.apply(2));
        }

    }

    private static Function<Integer, String> getDoubleThenToStringUsingMethodExp() {
        return MethodReferenceTest1::doubleThenToString;
    }

    private static String doubleThenToString(int i) {
        return String.valueOf(i * 2);
    }

    private static String tripleThenToString(int i) {
        return String.valueOf(i * 3);
    }

    private static String testFirstClassFunction01(int n, Function<Integer, String> f) {
        return "The Result is " + f.apply(n);
    }
    private static <T, R> Function<Integer, String> getDoubleThenToStringUsingLambdaExp() {
        return i -> String.valueOf(i * 2 );
    }
}

class BigDecimalUtil {
    public static int compare(BigDecimal bd1, BigDecimal bd2) {
        return bd1.compareTo(bd2);
    }
}