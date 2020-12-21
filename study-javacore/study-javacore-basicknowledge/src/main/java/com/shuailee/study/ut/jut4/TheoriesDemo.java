package com.shuailee.study.ut.jut4;

import org.junit.Assert;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;

@RunWith(Theories.class)
public class TheoriesDemo {

    @DataPoint
    public static String GOOD_USERNAME = "optim/us";
    @DataPoint
    public static String USERNAME_WITH_SLASH = "optimu/sprime";

    @Theory
    public void filenameIncludesUsername(String username) {
        Assert.assertThat(username, anyOf(containsString("/")));
    }
}
