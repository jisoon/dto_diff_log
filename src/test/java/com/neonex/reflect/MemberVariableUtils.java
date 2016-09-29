package com.neonex.reflect;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.*;


public class MemberVariableUtils {

    public static boolean isNotMatch(Object origin, Object current) throws IllegalAccessException {
        Class originClass = origin.getClass();
        Class currentClass = current.getClass();

        if (isNotEqualsClass(originClass, currentClass)) {
            throw new IllegalArgumentException("not equals class");
        }
        for (Field originField : FieldUtils.getAllFieldsList(originClass)) {
            String fieldName$ = originField.getName();
            if (isNotEqualsValue(origin, current, fieldName$)) {
                return true;
            }

        }
        return false;
    }

    private static void printNotEqualsVariableList(ArrayList<Map<String, Object>> notEqualsPropertyValueMessageList) {
        for (Map notEqualsMap : notEqualsPropertyValueMessageList) {
            System.out.println(Arrays.toString(notEqualsMap.entrySet().toArray()));
        }
    }

    private static boolean isNotEqualsClass(Class originClass, Class currentClass) {
        return !originClass.equals(currentClass);
    }

    private static boolean isNotEqualsValue(Object origin, Object current, String fieldName$) throws IllegalAccessException {
        return !FieldUtils.readField(origin, fieldName$, true).equals(FieldUtils.readField(current, fieldName$, true));
    }

    public static List<Map<String, Object>> fetchNotEqualsMemberVariable(UserDto origin, UserDto current) throws Exception {
        Class originClass = origin.getClass();
        Class currentClass = current.getClass();

        ArrayList<Map<String, Object>> notEqualsPropertyValueMessageList = new ArrayList<>();

        if (isNotEqualsClass(originClass, currentClass)) {
            throw new IllegalArgumentException("not equals class");
        }


        for (Field originField : FieldUtils.getAllFieldsList(originClass)) {
            String fieldName$ = originField.getName();
            if (isNotEqualsValue(origin, current, fieldName$)) {

                Map<String, Object> notEqualsInformation = new HashMap<>();
                notEqualsInformation.put("type", FieldUtils.getField(originClass, fieldName$, true).getType());
                notEqualsInformation.put("name", FieldUtils.getField(originClass, fieldName$, true).getName());
                notEqualsInformation.put("originValue", FieldUtils.readField(origin, fieldName$, true));
                notEqualsInformation.put("currentValue", FieldUtils.readField(current, fieldName$, true));
                notEqualsPropertyValueMessageList.add(notEqualsInformation);
            }

        }
        printNotEqualsVariableList(notEqualsPropertyValueMessageList);
        return notEqualsPropertyValueMessageList;
    }
}
