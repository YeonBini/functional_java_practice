package com.example.functional_java.closure;

public class Closure_Test1 {
    private int number = 999;

    public static void main(String[] args) {
        new Closure_Test1().test1();
    }

    public void test1() {
        // final이 아닌것 처럼 보이지만 final로 할당
        // Effectively final
//        int number = 100;

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println(number);
            }
        });

        testClosure("Lambda Expression", () -> System.out.println(this.number));


    }

    private static void testClosure(final String name, Runnable runnable) {
        System.out.println("============================");
        System.out.println("Start " + name + " : ");
        runnable.run();
        System.out.println("============================");
    }
}
