package com.exam.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class ReflectTest {
    List<String> list = Lists.newArrayList();

    @Test
    public void testReflect() throws NoSuchFieldException {

        Field listField = ReflectTest.class.getDeclaredField("list"); //获取 list 字段的泛型参数
        ParameterizedType listGenericType = (ParameterizedType) listField.getGenericType();
        Class clazz = (Class) listGenericType.getActualTypeArguments()[0];
        System.out.println(clazz.getSimpleName());

    }
}
