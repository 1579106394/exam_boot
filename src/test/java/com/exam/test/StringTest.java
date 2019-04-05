package com.exam.test;


import org.junit.Test;

public class StringTest {

    @Test
    public void testSubString() {
        String s = "abcde";
        System.out.println(s.substring(0, 2));
    }

    @Test
    public void testEquals() {
        String str = "1";
        Integer i = 1;
        System.out.println(str.equals(i));
    }

}
