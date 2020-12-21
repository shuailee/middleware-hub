package com.shuailee.study.basicknowledge.collectorstest;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: study-javacore
 * @description: 实现一套集合工具类
 * @author: shuai.li
 * @create: 2019-06-28 11:55
 **/
public class CollectionUtils {

    /**
     * Null-safe check if the specified collection is empty.
     * <p>
     * Null returns true.
     *
     * @param coll the collection to check, may be null
     * @return true if empty or null
     * @since Commons Collections 3.2
     */
    public static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }

    /**
     * Null-safe check if the specified collection is not empty.
     * <p>
     * Null returns false.
     *
     * @param coll the collection to check, may be null
     * @return true if non-null and non-empty
     * @since Commons Collections 3.2
     */
    public static boolean isNotEmpty(Collection coll) {
        return !isEmpty(coll);
    }



    /**
     * list查找第一个
     *
     * @param listData
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> T findFirst(Collection<T> listData, Predicate<? super T> predicate) {
        if (isNullOrEmpty(listData)) {
            return null;
        }
        Stream<T> streamList = listData.stream().filter(predicate);
        Optional<T> firstResult = streamList.findFirst();
        return firstResult.orElse(null);
    }

    /**
     * 是否存在
     *
     * @param listData
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> boolean exists(Collection<T> listData, Predicate<? super T> predicate) {
        return findFirst(listData, predicate) != null;
    }

    /**
     * 是否全部匹配
     *
     * @param listData
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> boolean all(Collection<T> listData, Predicate<? super T> predicate) {
        return listData.stream().allMatch(predicate);
    }

    /**
     * 是否任何一个匹配
     *
     * @param listData
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> boolean any(Collection<T> listData, Predicate<? super T> predicate) {
        return listData.stream().anyMatch(predicate);
    }

    /**
     * 获取符合条件的数据
     *
     * @param listData
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> List<T> select(Collection<T> listData, Predicate<? super T> predicate) {
        return listData.stream().filter(predicate).collect(Collectors.toList());
    }

    public static <T, R> List<R> selectProp(Collection<T> listData, Function<? super T, ? extends R> mapper) {
        return listData.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 数量
     *
     * @param listData
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> int count(Collection<T> listData, Predicate<? super T> predicate) {
        return select(listData, predicate).size();
    }

    /**
     * 不为空
     *
     * @param listData
     * @return
     */
    public static boolean isNotNull(Collection listData) {
        return listData != null && listData.size() > 0;
    }

    /**
     * 为空
     *
     * @param listData
     * @return
     */
    public static boolean isNullOrEmpty(Collection listData) {
        return listData == null || listData.size() == 0;
    }

    /**
     * 分组
     *
     * @param listData
     * @param classifier
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> Map<K, List<T>> groupBy(Collection<T> listData, Function<? super T, ? extends K> classifier) {
        return listData.stream().collect(Collectors.groupingBy(classifier));
    }

    /**
     * 排序
     *
     * @param listData
     * @param classifier
     * @param <T>
     * @return
     */
    public static <T> List<T> orderBy(Collection<T> listData, Function<? super T, ? extends Comparable> classifier) {
        Stream<T> sortResult = listData.stream().sorted(Comparator.comparing(classifier::apply));
        return sortResult.collect(Collectors.toList());
    }

    /**
     * 最大值
     *
     * @param listData
     * @param <T>
     * @return
     */
    public static <T> T max(Collection<T> listData, Function<? super T, ? extends Comparable> classifier) {
        Optional<T> optional = listData.stream().max(Comparator.comparing(classifier::apply));
        return optional.orElse(null);
    }

    /**
     * 最小值
     *
     * @param listData
     * @param <T>
     * @return
     */
    public static <T> T min(Collection<T> listData, Function<? super T, ? extends Comparable> classifier) {
        Optional<T> optional = listData.stream().min(Comparator.comparing(classifier::apply));
        return optional.orElse(null);
    }

    /**
     * 求和
     *
     * @param listData
     * @param classifier
     * @param <T>
     */
    public static <T> int sumInt(Collection<T> listData, Function<? super T, ? extends Integer> classifier) {
        return listData.stream().mapToInt(classifier::apply).sum();
    }

    /**
     * 求和
     *
     * @param listData
     * @param classifier
     * @param <T>
     */
    public static <T> double sumDouble(Collection<T> listData, Function<? super T, ? extends Double> classifier) {
        return listData.stream().mapToDouble(classifier::apply).sum();
    }

    /**
     * 求和
     *
     * @param listData
     * @param classifier
     * @param <T>
     */
    public static <T> long sumLong(Collection<T> listData, Function<? super T, ? extends Long> classifier) {
        return listData.stream().mapToLong(classifier::apply).sum();
    }

    /**
     * 求和
     *
     * @param listData
     * @param classifier
     * @param <T>
     * @return
     */
    public static <T> BigDecimal sumDecimal(Collection<T> listData, Function<? super T, ? extends BigDecimal> classifier) {
        List<BigDecimal> decimalList = selectProp(listData, classifier);
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal bigDecimal : decimalList) {
            if (bigDecimal == null) {
                continue;
            }
            result = result.add(bigDecimal);
        }
        return result;
    }

    /**
     * 去重
     *
     * @param listData
     * @param <T>
     * @return
     */
    public static <T> List<T> distinct(Collection<T> listData) {
        return listData.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 取第一个
     *
     * @param listData
     * @param <T>
     * @return
     */
    public static <T> T first(Collection<T> listData) {
        if (isNullOrEmpty(listData)) {
            return null;
        }
        return listData.stream().findFirst().orElse(null);
    }

    /**
     * 取最后一个
     *
     * @param listData
     * @param <T>
     * @return
     */
    public static <T> T last(List<T> listData) {
        if (isNullOrEmpty(listData)) {
            return null;
        }
        return listData.get(listData.size() - 1);
    }

    /**
     * 移除全部符合条件的数据
     *
     * @param listData
     * @param predicate
     * @param <T>
     */
    public static <T> List<T> removeAll(Collection<T> listData, Predicate<? super T> predicate) {
        List<T> removeData = select(listData, predicate);
        listData.removeAll(removeData);
        return removeData;
    }
}
