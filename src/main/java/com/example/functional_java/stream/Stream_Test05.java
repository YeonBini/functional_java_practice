package com.example.functional_java.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Stream_Test05 {

    private static final String[] priceString = {"1.0" , "100.99", "35.76", "21.30", "88.00"};
    private static final Random random = new Random(123);
    private static final BigDecimal[] targetPrices = {new BigDecimal("30"), new BigDecimal("20"), new BigDecimal("31")};
    private static final Random targetPricesRandom = new Random(111);

    private static final List<Product> products;

    static {
        final int length = 8_000_000;
        /**
         * list의 경우 10만 기준으로 계속 length를 변경해야하기에
         * 배열로 미리 크기를 할당하여 리스트를 만드는 것이 효율적임
         */
//        final List<Product> list = new ArrayList<>(length);
        final Product [] list = new Product[length];
        for(int i =1; i<=length; i++) {
            list[i-1] = (new Product((long) i,
                    "Product"+ i,
                    new BigDecimal(priceString[random.nextInt(5)])));
        }
//        products = Collections.unmodifiableList(list);
        products = Arrays.asList(list);
    }

    private static BigDecimal imperativeSum(final List<Product> products,
                                            final Predicate<Product> predicate) {
        BigDecimal sum = BigDecimal.ZERO;
        for(final Product product : products) {
            if(predicate.test(product)) {
                sum = sum.add(product.getPrice());
            }
        }
        return sum;
    }

    private static BigDecimal streamSum(final Stream<Product> stream,
                                        final Predicate<Product> predicate) {
        return stream.filter(predicate)
                     .map(Product::getPrice)
                     .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private static void imperativeTest(final BigDecimal targetPrice,
                                       final boolean printResult) {
        if(printResult) {
            System.out.println("=============================");
            System.out.println("\nImperative Sum\n---------------------------");
        }
        final long start = System.currentTimeMillis();
        final BigDecimal result = imperativeSum(products, product -> product.getPrice().compareTo(targetPrice) >=0);
        final long howLong = System.currentTimeMillis() - start;
        if(printResult) {
            System.out.println("Sum : " + result);
            System.out.println("It took " + howLong +" ms.");
            System.out.println("=============================");
        }

    }
    private static void streamTest(final BigDecimal targetPrice,
                                       final boolean printResult) {
        if(printResult) {
            System.out.println("=============================");
            System.out.println("\nStream Sum\n---------------------------");
        }
        final long start = System.currentTimeMillis();
        final BigDecimal result = streamSum(products.stream(), product -> product.getPrice().compareTo(targetPrice) >=0);
        final long howLong = System.currentTimeMillis() - start;
        if(printResult) {
            System.out.println("Sum : " + result);
            System.out.println("It took " + howLong +" ms.");
            System.out.println("=============================");
        }

    }
    private static void parallelStreamTest(final BigDecimal targetPrice,
                                       final boolean printResult) {
        if(printResult) {
            System.out.println("=============================");
            System.out.println("\nParallel Stream Sum\n---------------------------");
        }
        final long start = System.currentTimeMillis();
        final BigDecimal result = streamSum(products.parallelStream(), product -> product.getPrice().compareTo(targetPrice) >=0);
        final long howLong = System.currentTimeMillis() - start;
        if(printResult) {
            System.out.println("Sum : " + result);
            System.out.println("It took " + howLong +" ms.");
            System.out.println("=============================");
        }

    }

    public static void main(String[] args) {
        final BigDecimal targetPrice = new BigDecimal("40");

        streamTest(targetPrice, false);
        parallelStreamTest(targetPrice, false);
        imperativeTest(targetPrice, false);

        for(int i=0; i<5; i++) {
            BigDecimal price = targetPrices[targetPricesRandom.nextInt(3)];

            parallelStreamTest(price, true);
            streamTest(price, true);
            imperativeTest(price, true);
        }
    }
}


@Data
@AllArgsConstructor
class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}
