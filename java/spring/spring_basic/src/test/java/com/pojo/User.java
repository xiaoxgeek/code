package com.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by XiaoX on 2018/12/5.
 */
@Component
@PropertySource(value = {"classpath:config/test.properties"})
public class User {
    @Value("${user.name}")
    private String name;
    @Value("${user.age}")
    private int age;
    @Value("${user.gender}")
    private int gender;
    @Value("${user.birth}")
    private Date birth;
    @Value("${user.hobby}")
    private List<String> hobby;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", birth=" + birth +
                ", hobby=" + hobby +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }
}
