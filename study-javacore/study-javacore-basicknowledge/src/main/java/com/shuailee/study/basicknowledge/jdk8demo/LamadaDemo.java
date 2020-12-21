package com.shuailee.study.basicknowledge.jdk8demo;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LamadaDemo {

    public static void main(String[] args) {
        // threaddemo();

        //fordemo();

        //predicateDemo();

        //maptest();

        //maxvalue();

        //distinct();

        // sumtest();

         //funref();

        //supplierTest();
        consumerTest();


    }

    /**
     * consumer接口：对输入的参数进行操作。有输入没输出
     */
    private static void consumerTest(){
        Consumer<Integer> add5 = (p) -> {
            System.out.println("old value:" + p);
            p = p + 5;
            System.out.println("new value:" + p);
        };
        add5.accept(10);
    }

    /**
     * Supplier接口：返回一个给定类型的结果。不需要输入参数，无输入有输出
     */
    private static void supplierTest(){
        Supplier<String> supplier = () -> "我就是输出";
        String s = supplier.get();
        System.out.println(s);
    }


    //region --方法引用

    private static void funref() {
        //传统方式
        Transform<String, Integer> transform1 = new Transform<String, Integer>() {
            @Override
            public Integer transform(String s) {
                return new Obj().strToInt(s);
            }
        };
        int result1 = transform1.transform("100");
        System.out.println(result1);

        //Lambda表达式
        Obj obj = new Obj();
        //在lambda表达式中，方法引用是一种简化写法，引用的方法就是Lambda表达式的方法体的实现 方法
        Transform<String, Integer> transform2 = obj::strToInt;
        int result2 = transform2.transform("200");
        System.out.println(result2);
    }

    /**
     * 函数式接口
     * 只包含一个接口定义的接口被称为函数式接口
     * @param <A>
     * @param <B>
     */
    interface Transform<A, B> {
        B transform(A a);
    }

    /**
     * 实例对象类
     */
    static class Obj {
        public int strToInt(String str) {
            return Integer.valueOf(str);
        }
    }


    //endregion


    //region --局部变量
    private static void sumtest() {
        int num = 6;//局部变量
        Sum sum = value -> {
            //这里面不能更改num的值
            return num + value;
        };
        System.out.println(sum.add(10));
    }

    /**
     * 函数式接口
     */
    interface Sum {
        int add(int value);
    }

    //endregion

    //region --Predicate
    private static void predicateDemo() {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Languages which starts with J :");
        //传入一个Predicate
        filter(languages, (str) -> str.toString().startsWith("J"));

        //传入一个Predicate
        Predicate p1 = (str) -> str.toString().startsWith("J");
        Predicate p2 = (str) -> str.toString().length() > 2;
        //
        filter(languages, p1.and(p2));
    }


    public static void filter(List<String> names, Predicate condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    public static void filter2(List<String> names, Predicate condition) {
        names.stream().filter(condition)
                .forEach((name) -> {
                    System.out.println(name + " ");
                });
    }

    //endregion


    private static void fordemo() {
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API",
                "Date and Time API");
        features.forEach(n -> System.out.println(n));
    }

    private static void threaddemo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }).start();

        new Thread(() -> System.out.println("hello world")).start();
    }


    public static void maptest() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        // costBeforeTax.stream().map((s) -> s * 10).forEach(System.out::println);
        //reduce() 是将集合中所有值结合进一个，Reduce类似SQL语句中的sum(), avg() 或count(),
        Integer bill = costBeforeTax.stream().reduce((a, b) -> a + b).get();
        System.out.println(bill);
    }

    //计算List中的元素的最大值，最小值，总和及平均值
    public static void maxvalue() {
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x)
                .summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }

    //使用Stream的distinct()方法过滤集合中重复元素。
    public static void distinct() {
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(i -> i * i).distinct()
                .collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
    }
}
