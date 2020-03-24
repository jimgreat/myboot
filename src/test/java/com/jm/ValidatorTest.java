package com.jm;


import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
class StudentInfo {

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "学校不能为空")
    private String school;

    @NotBlank(message="年龄不能为空")
    @Pattern(regexp="^[0-9]{1,2}$",message="年龄是整数")
    @Pattern(regexp="^[0-9]{1,2}$",message="年龄是整数2")
    private String age;
}

public class ValidatorTest {

    public static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> Map<String, String> validate(T obj) {
        Map<String, String> errorMap = new HashMap<>();

        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && set.size() > 0) {

            errorMap = set.stream().collect(Collectors.groupingBy(cv -> cv.getPropertyPath().toString()))
                    .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                    i->i.getValue().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","))));

        }
        return errorMap;
    }

    private static void print(Map<String, String> errorMap) {
        if (errorMap != null) {
            for (Map.Entry<String, String> m : errorMap.entrySet()) {
                System.out.println(m.getKey() + ":" + m.getValue().toString());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("VT");
        StudentInfo s = new StudentInfo();
        s.setAge("A");
        print(validate(s));


    }

}
