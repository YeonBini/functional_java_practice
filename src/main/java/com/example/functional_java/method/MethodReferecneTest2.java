package com.example.functional_java.method;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.function.Function;

public class MethodReferecneTest2 {
    public static void main(String[] args) {
        final Section section = new Section(1);
        final Function<Integer, Section> sectionFactory
                = number -> new Section(number);
        final Section sectionWithLambdaExp = sectionFactory.apply(1);

        System.out.println(section);
        System.out.println(sectionWithLambdaExp);

        final Function<Integer, Section> sectionFactoryWithMethodReference
                = Section::new;
        System.out.println(sectionFactoryWithMethodReference.apply(2));


        final OldProduct product = new OldProduct(1L, "A", new BigDecimal("100.00"));

        System.out.println(product);

        final OldProductCreator oldProductCreator = OldProduct::new;

        System.out.println(oldProductCreator.create(1L, "A", new BigDecimal("100.00")));

        final ProductA productA = createProduct(1L, "A", new BigDecimal("123.00"), ProductA::new);
        final ProductB productB = createProduct(2L, "B", new BigDecimal("123.00"), ProductB::new);

        System.out.println(productA);
        System.out.println(productB);

    }

    private static <T extends Product> T createProduct(final Long id,
                                                final String name,
                                                final BigDecimal price,
                                                final ProductCreator<T> productCreator) {
        if(id == null || id < 1 ) {
            throw new IllegalArgumentException("Id must be a positive Long");
        }
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is not given");
        }
        if(price == null || price.compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new IllegalArgumentException("Price must be positive number");
        }

        return productCreator.create(id, name, price);
    }
}
@FunctionalInterface
interface OldProductCreator {
    OldProduct create(Long id, String name, BigDecimal price);
}


@FunctionalInterface
interface ProductCreator<T extends Product> {
    T create(Long id, String name, BigDecimal price);
}

@Data
@AllArgsConstructor
class OldProduct {
    private Long id;
    private String name;
    private BigDecimal price;
}

@Data
@AllArgsConstructor
abstract class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}

class ProductA extends Product {

    public ProductA(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "ProductA = " + super.toString();
    }
}

class ProductB extends Product {

    public ProductB(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "ProductB = " + super.toString();
    }
}
@Data
@AllArgsConstructor
class Section{
    private int number;
}