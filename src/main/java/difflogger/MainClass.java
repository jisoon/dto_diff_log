package difflogger;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 지순
 * @since : 2016-09-29
 */
public class MainClass {

    public static void main(String[] ar) {
        UserDto originInfo = new UserDto("지순", 37);
        originInfo.setPhoneNum("01012341234");

        UserDto currentInfo = new UserDto("하영", 37);
        currentInfo.setPhoneNum("01098769876");

        Class originInfoClass = originInfo.getClass();
        Class currentInfoClass = currentInfo.getClass();

        ArrayList<Map<String, Object>> notEqualsPropertyValueMessageList = new ArrayList<>();

        if (originInfoClass.equals(currentInfoClass)) {
            for (Field originField : FieldUtils.getAllFieldsList(originInfoClass)) {
                String fieldName$ = originField.getName();
                try {
                    if (!FieldUtils.readField(originInfo, fieldName$, true).equals(FieldUtils.readField(currentInfo, fieldName$, true))) {
                        Map<String, Object> notEqualsInformation = new HashMap<>();
                        notEqualsInformation.put("type", FieldUtils.getField(originInfoClass, fieldName$, true).getType());
                        notEqualsInformation.put("name", FieldUtils.getField(originInfoClass, fieldName$, true).getName());
                        notEqualsInformation.put("originValue", FieldUtils.readField(originInfo, fieldName$, true));
                        notEqualsInformation.put("currentValue", FieldUtils.readField(currentInfo, fieldName$, true));
                        notEqualsPropertyValueMessageList.add(notEqualsInformation);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new IllegalArgumentException("두 오브젝트의 클래스가 다릅니다.");
        }

        for (Map notEqualsMap : notEqualsPropertyValueMessageList) {
            System.out.println(Arrays.toString(notEqualsMap.entrySet().toArray()));
        }

    }
}

class UserDto {
    private String name;
    private int age;
    private String phoneNum;

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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "logger.UserDto{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

