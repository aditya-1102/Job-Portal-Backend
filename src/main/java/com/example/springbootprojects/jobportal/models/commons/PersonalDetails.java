package com.example.springbootprojects.jobportal.models.commons;

import com.example.springbootprojects.jobportal.constants.UserConstants;

import java.util.Date;

public class PersonalDetails {

    private int age;
    private Date dob;
    private String email;
    private UserConstants.GENDER gender;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserConstants.GENDER getGender() {
        return gender;
    }

    public void setGender(UserConstants.GENDER gender) {
        this.gender = gender;
    }
}
