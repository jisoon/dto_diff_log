package com.neonex.reflect;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class MemberVariableUtilsTest {

    @Before
    public void setUp(){

    }

    @Test
    public void notEqualsBooleanTest() throws Exception {
        // given
        UserDto jisoon = new UserDto();
        jisoon.setAge(37);
        jisoon.setName("jisoon");
        jisoon.setPhone("01012341234");

        UserDto hayoung = new UserDto();
        hayoung.setAge(37);
        hayoung.setName("hayoun");
        hayoung.setPhone("01098769876");


        assertThat(MemberVariableUtils.isNotMatch(jisoon, hayoung), is(true));
    }

    @Test
    public void notEqualsMemberVariableListTest() throws Exception{
        // given
        UserDto jisoon = new UserDto();
        jisoon.setAge(37);
        jisoon.setName("jisoon");
        jisoon.setPhone("01012341234");

        UserDto hayoung = new UserDto();
        hayoung.setAge(37);
        hayoung.setName("hayoun");
        hayoung.setPhone("01098769876");

        List<Map<String, Object>> notEqualsList = MemberVariableUtils.fetchNotEqualsMemberVariable(jisoon, hayoung);

        assertThat(notEqualsList.size(), is(2));
    }
}


class UserDto {
    private String name;
    private String phone;
    private int age;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
