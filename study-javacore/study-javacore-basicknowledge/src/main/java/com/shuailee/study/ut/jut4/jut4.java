package com.shuailee.study.ut.jut4;

import org.hamcrest.core.CombinableMatcher;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.hamcrest.core.Every.everyItem;

public class jut4 {

    @Test
    public void versionSwitchTest(){
        Assert.assertTrue(BizUtil.versionSwitch(107,107));
    }
    @Test
    public void testArraysEquals(){
        byte[] expected="trial".getBytes();
        byte[] actual ="trial".getBytes();
        Assert.assertArrayEquals(expected,actual);
    }
    @Test
    public void testAssertEquals() {
        /**
         * 第一个参数为失败描述
         * */
        Assert.assertEquals("failure - strings are not equal", "text", "text");
    }

    /**
     * 不是同一个对象
     * */
    @Test
    public void testAssertNotSame() {
        Assert.assertNotSame("should not be same Object", new Object(), new Object());
    }

    @Test
    public void testAssertSame() {
        Integer aNumber = Integer.valueOf(768);
        Integer bNumber=768;
        Integer cNumber=768;
        Integer dNumber = Integer.valueOf(768);
        Assert.assertEquals("should be same", aNumber, bNumber);
        Assert.assertNotSame("should be same", aNumber, bNumber);
        Assert.assertNotSame("should be same", bNumber, cNumber);
        Assert.assertNotSame("should be same", aNumber, dNumber);

        String a="time";
        String b="time";
        Assert.assertSame(a,b);
    }


    // JUnit Matchers assertThat
    @Test
    public void testAssertThatBothContainsString() {
        Assert.assertThat("albumen", both(containsString("a")).and(containsString("b")));
    }

    @Test
    public void testAssertThatHasItems() {
        /**
         * 包含某个元素
         * */
        Assert.assertThat(Arrays.asList("one", "two", "three"), hasItem("one"));

        /**
         * 每个元素包含某个字符
         * */
        Assert.assertThat(Arrays.asList(new String[] { "fun", "ban", "net" }), everyItem(containsString("n")));
    }

    @Test
    public void testAssertThatEveryItemContainsString() {
        Assert.assertThat(Arrays.asList(new String[] { "fun", "ban", "net" }), everyItem(containsString("n")));
    }


    // Core Hamcrest Matchers with assertThat
    @Test
    public void testAssertThatHamcrestCoreMatchers() {
        Assert.assertThat("good", allOf(equalTo("good"), startsWith("good")));
        Assert.assertThat("good", not(allOf(equalTo("bad"), equalTo("good"))));
        Assert.assertThat("good", anyOf(equalTo("bad"), equalTo("good")));
        Assert.assertThat(7, not(CombinableMatcher.<Integer> either(equalTo(3)).or(equalTo(4))));
        Assert.assertThat(new Object(), not(sameInstance(new Object())));
    }


    /**
     * 如果没有抛出指定异常则测试不通过
     * */
    @Test(expected = IndexOutOfBoundsException.class)
    public void empty() {
        new ArrayList<Object>().get(0);
    }

    /**
     *让测试暂时失效
     **/
    @Ignore("Test is ignored as a demonstration")
    @Test(timeout=1000) //还可以指定测试失效时间
    public void testSame() {
        Assert.assertThat(1, is(1));
    }
}

class BizUtil{
    public static  boolean versionSwitch(Integer sourceVersion,Integer targetVersion){
        int result = Integer.compare(sourceVersion,targetVersion);
        return result>=0;
    }
}





