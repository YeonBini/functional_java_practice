package com.example.functional_java.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

public class Stream_Test05 {

    private static final String[] priceString = {"1.0" , "100.99", "35.76", "21.30", "88.00"};
    private static final Random random = new Random(123);

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



    public static void main(String[] args) {

    }
}


@Data
@AllArgsConstructor
class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}
