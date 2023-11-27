package com.chatapp.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CommonUtils {
    public static <T> T mapToObject(Map<String, Object> source, Class<T> target) {
        Field[] fields = target.getDeclaredFields();
        T object = null;
        try {
            object = target.newInstance();
            for (Field field : fields) {
                String fieldName = field.getName();
                try {
                    if (source.get(fieldName) != null) {
                        BeanUtils.setProperty(object, field.getName(), source.get(field.getName()));
                    }
                } catch (InvocationTargetException e) {
                    return object;
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }

        return object;
    }

    public static <T> T getValue(Object value, Class<T> tClass) {
        if (value == null) {
            return null;
        }
        return tClass.cast(value);
    }
}
