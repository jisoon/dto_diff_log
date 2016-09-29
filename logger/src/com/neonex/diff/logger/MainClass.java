package com.neonex.diff.logger;

import java.lang.reflect.Field;

/**
 * @author : 지순
 * @since : 2016-09-29
 */
public class MainClass {

    public static void main(String[] ar) {
        UserDto originInfo = new UserDto("지순", 37);
        UserDto currentInfo = new UserDto("하영", 36);

        Field[] orginInfoFields = originInfo.getClass().getDeclaredFields();

        for (Field filed : orginInfoFields) {
            System.out.println(filed.getName());
        }
    }
}

class UserDto{
    private String name;
    private int age;

    public UserDto() {
    }

    public UserDto(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "com.neonex.diff.logger.UserDto{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

