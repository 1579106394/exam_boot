package com.exam.test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * java编译程序
 *
 * @version 1.0
 * @author: 杨德石
 * @date: 2019/4/19 0019 下午 8:12
 */
public class CompileTest {

    /**
     * 编译C程序
     *
     * @throws Exception
     */
    @Test
    public void testCompileJava() throws Exception {
        Runtime.getRuntime().exec("javac HelloWorld.java", null, new File("D:\\MyProject\\compile\\"));
        Process process = Runtime.getRuntime().exec("java HelloWorld", null, new File("D:\\MyProject\\compile\\"));
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());
        bufferedReader.close();
        inputStream.close();
    }

}
