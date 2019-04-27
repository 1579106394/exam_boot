package com.exam.test;


import com.exam.constant.PatternConstant;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

    private final Pattern pattern = PatternConstant.THREE_UNDER_LINE_PATTERN;

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

    @Test
    public void testRegex() {
        String str = "___哈哈___测__试第一个空___第二个空___第三个___哈哈哈_______";
        Matcher matcher = pattern.matcher(str);
        String s = matcher.replaceAll("");
        System.out.println(s);
    }

    @Test
    public void testChar() {
        System.out.println("1".equals('1'));
    }

}
